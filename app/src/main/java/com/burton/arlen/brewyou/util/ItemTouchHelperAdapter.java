package com.burton.arlen.brewyou.util;

/**
 * Created by Guest on 6/14/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
