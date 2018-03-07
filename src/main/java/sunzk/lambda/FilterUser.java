package sunzk.lambda;

/**
 * Created by 82681 on 2018/1/5.
 */
public interface FilterUser<T> {
    public boolean test(T user);
}
