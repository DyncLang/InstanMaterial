package cn.zlpro.cn.instanmaterial.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ViewAnimator;

import cn.zlpro.cn.instanmaterial.R;

/**
 * Created by 小白龙 on 2016/1/5 0005.
 */
public class SendCommentButton extends ViewAnimator implements View.OnClickListener
{
    public static final int STATE_SEND = 0;
    public static final int STATE_DONE = 1;


    private static final long RESET_STATE_DELAY_MILLIS = 2000;

    private int currentState;
    Runnable revertStateRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            setCurrentState(STATE_DONE);
        }
    };


    public SendCommentButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public SendCommentButton(Context context)
    {
        super(context);
        init();
    }

    private void init()
    {
        LayoutInflater.from(getContext()).inflate(R.layout.view_send_comment_button, this, true);
    }

    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        currentState = STATE_SEND;
        super.setOnClickListener(this);
    }

    @Override
    protected void onDetachedFromWindow()
    {

        removeCallbacks(revertStateRunnable);
        super.onDetachedFromWindow();
    }


    public void setCurrentState(int state)
    {
        if (state == currentState)
            return;
        currentState = state;

        if (state == STATE_DONE) {
            setEnabled(false);
            postDelayed(revertStateRunnable, RESET_STATE_DELAY_MILLIS);
            setInAnimation(getContext(), R.anim.slide_in_done);
            setOutAnimation(getContext(), R.anim.slide_out_send);
        } else if (state == STATE_SEND) {
            setEnabled(true);
            setInAnimation(getContext(), R.anim.slide_in_send);
            setOutAnimation(getContext(), R.anim.slide_out_done);
        }

        Log.i("xbl", "setCurrentState: ");
        showNext();
    }

    @Override
    public void onClick(View v)
    {
        if (onSendClickListener != null)
        {
            onSendClickListener.onSendClick(v);
        }
    }

    public void setOnSendClickListener(OnSendClickListener onSendClickListener)
    {
        this.onSendClickListener = onSendClickListener;
    }

    OnSendClickListener onSendClickListener;

    public interface OnSendClickListener
    {
        public void onSendClick(View v);
    }
}
