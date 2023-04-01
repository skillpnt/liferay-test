package shop.crud.portlet.portlet;

import shop.model.Purchase;

import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class PurchaseDateComparator implements Comparator<Purchase> {

    @Override
    public int compare(Purchase o1, Purchase o2) {
        Date date1 = o1.getPurchaseDate();
        Date date2 = o2.getPurchaseDate();
        return date1.compareTo(date2);
    }

    @Override
    public Comparator<Purchase> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator<Purchase> thenComparing(Comparator<? super Purchase> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public <U> Comparator<Purchase> thenComparing(Function<? super Purchase, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Purchase> thenComparing(Function<? super Purchase, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator<Purchase> thenComparingInt(ToIntFunction<? super Purchase> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator<Purchase> thenComparingLong(ToLongFunction<? super Purchase> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<Purchase> thenComparingDouble(ToDoubleFunction<? super Purchase> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }
}
