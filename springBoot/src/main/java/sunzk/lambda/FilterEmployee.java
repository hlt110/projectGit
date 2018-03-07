package sunzk.lambda;

import java.util.List;

/**
 * Created by 82681 on 2018/1/5.
 */
public interface FilterEmployee<T> {
    public List<T> sort(T m, T m1);
}
