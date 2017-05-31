package in.keatz.components.data.remote;

import java.util.List;

/**
 * Created by Inspiron on 5/25/2017.
 */

public interface NetworkInteractor<T> {
    public void onSyncData(List<T> data);
    public void onFailed(T error);
}
