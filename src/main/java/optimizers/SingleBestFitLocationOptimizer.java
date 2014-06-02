package optimizers;

import algorithms.Algorithm;
import calculations.SubscriberCenter;
import calculations.Terrain;
import com.sun.corba.se.spi.activation._ActivatorImplBase;
import de.fhpotsdam.unfolding.geo.Location;
import javafx.util.Pair;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.integration.*;
import views.map.BTS;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Ja on 01.06.14.
 */
public class SingleBestFitLocationOptimizer implements optimizers.IBTSLocationOptimizer, Algorithm {
    List<BTS> availableBTSs = new LinkedList<BTS>();
    List<SubscriberCenter> availableSCs = new LinkedList<SubscriberCenter>();

    private void loadBTSs(List<BTS> btss)
    {
        for(BTS bts : btss)
            availableBTSs.add(bts);
    }

    private void loadSCs(List<SubscriberCenter> scs)
    {
        for(SubscriberCenter sc : scs)
            availableSCs.add(sc);
    }

    double impact(SubscriberCenter sc)
    {
        org.javatuples.Pair<Double, Double> variance = sc.getVariance();
        return sc.getMaxRequiredSignal()*variance.getValue0()*variance.getValue1();
    }

    private SubscriberCenter getSCWithBiggestImpact()
    {
        SubscriberCenter sc = availableSCs.get(0);
        for(SubscriberCenter nsc: availableSCs)
        {
            if(impact(sc) < impact(nsc))
                sc = nsc;
        }

    return sc;
    }

    @Override
    public Terrain regenerateTerrain(Terrain currentTerrain) {
        relocate(currentTerrain);
        return currentTerrain;
    }

    @Override
    public void setBtsCount(int btsCount) {

    }

    @Override
    public void setSubscriberCenterCount(int subscriberCenterCount) {

    }

    private class SignalVsRequired implements UnivariateFunction
    {
        double scReq;
        double btsDelivers;

        SignalVsRequired(double scRequirement, double btsMax)
        {
            scReq = scRequirement;
            btsDelivers = btsMax;
        }

        @Override
        public double value(double r) {
            double val = scReq - btsDelivers / (r*r + 1);
            if(val < 0)
                val = -val/3;

            return val;
        }
    }

    private BTS findBestFittingBTS(SubscriberCenter sc)
    {
        // in theory: minimizing integral over SC ellipsis
        // but that analytically unsolvable, and tough to solve numerically
        // assuming circle with radius = bigger variance
        // minimizing integral over |k1 - k2 / (r^2 + 1)| by r (0 to r)
        // and by alpha -> 0 to 2 pi
        // second is only multiplication by constant, so it's omitted
        // in fact, negative values (too high signal) count third as much as too low signal.
        UnivariateIntegrator integrator = new SimpsonIntegrator(1,10,1,50);

        double radius = (sc.getVariance().getValue0() > sc.getVariance().getValue1()) ? sc.getVariance().getValue0() : sc.getVariance().getValue1();
        BTS best = availableBTSs.get(0);
        double bestFitness = integrator.integrate(99, new SignalVsRequired(sc.getMaxRequiredSignal(), best.getMaxSignalLevel()), 0, radius);
        for(BTS bts: availableBTSs)
        {
            double currentFitness = integrator.integrate(99, new SignalVsRequired(sc.getMaxRequiredSignal(), bts.getMaxSignalLevel()), 0, radius);
            if(currentFitness < bestFitness)
            {
                bestFitness = currentFitness;
                best = bts;
            }
        }

        return best;
    }

    @Override
    public void relocate(Terrain t) {
        loadBTSs(t.getBtss());
        loadSCs(t.getSubscriberCenters());
        System.out.println(String.format("Placing %d BTSs using SingleBestFit algorithm", availableBTSs.size()));

        while(!availableSCs.isEmpty() && !availableBTSs.isEmpty())
        {
            SubscriberCenter biggestImpact = getSCWithBiggestImpact();
            BTS bestFitting = findBestFittingBTS(biggestImpact);
            bestFitting.setLocation(biggestImpact.getLocation());
            availableBTSs.remove(bestFitting);
            availableSCs.remove(biggestImpact);
            System.out.println(String.format("Remaining BTSs: %d", availableBTSs.size()));
        }

        if(!availableBTSs.isEmpty())
        {
            new GreedyLocationOptimizer().optimize(t, availableBTSs);
        }
    }
}
