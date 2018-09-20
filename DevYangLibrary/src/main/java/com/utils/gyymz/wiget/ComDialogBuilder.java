package com.utils.gyymz.wiget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lsjr.net.R;


/**
 * 类名称：对话框工具类 类描述：创建对话框的工具类，可以设置不同样式，和动画风格
 * <p>
 * 创建人： zhl 创建时间：2012-1-20 上午10:37:55
 */
public class ComDialogBuilder {


    /**
     * 从下往上滑动动画
     */
    public static final int DIALOG_ANIM_SLID_BOTTOM = R.style.DialogAnimationSlidBottom;
    /**
     * 缩放动画
     */
    public static final int DIALOG_ANIM_NORMAL = R.style.DialogAnimation;
    /**
     * 普通对话框
     */
    public static final int DIALOG_STYLE_NORMAL = R.layout.cb_dialog;

    /**
     * 对话框宽度所占屏幕宽度的比例
     */
    public static final float WIDTHFACTOR = 0.75f;
    /**
     * 对话框透明比例
     */
    public static final float ALPHAFACTOR = 1.0f;
    /**
     * 对话框处于屏幕顶部位置
     */
    public static final int DIALOG_LOCATION_TOP = 12;
    /**
     * 对话框处于屏幕中间位置
     */
    public static final int DIALOG_LOCATION_CENTER = 10;
    /**
     * 对话框处于屏幕底部位置
     */
    public static final int DIALOG_LOCATION_BOTTOM = 11;
    /**
     * 消息位于对话框的位置 居左
     */
    public static final int MSG_LAYOUT_LEFT = 1;
    /**
     * 消息位于对话框的位置 居中
     */
    public static final int MSG_LAYOUT_CENTER = 0;
    /**
     * 当前使用的风格
     */
    private int DIALOG_STYLE_CURRENT = DIALOG_STYLE_NORMAL;
    /**
     * 上下文
     */
    private Context context;
    /**
     * Dialog对象
     */
    private Dialog dialog;
    /**
     * 右边（确定）按钮
     */
    private Button confrimBtn;
    /*
     * confrim btn background resID
     */
    private int confirmBtnBG;
    /**
     * 左边（取消）按钮
     */
    private Button cancelBtn;
    /*
    * cancel btn background resID
    */
    private int cancelBtnBG;
    /**
     * 按钮中间分隔线
     */
    private View verticleLine;
    /**
     * 按钮水平分隔线
     */
    private View horizatonalLine;
    /**
     * dialog 根布局
     */
    private View dialogRootLayout;
    /**
     * 消息框布局
     */
    ViewGroup msglayout;
    /**
     * 是否显示cancelbutton;
     */
    private boolean showCancelButton = false;
    /**
     * 是否点击对话框外面取消对话框
     */
    private boolean touchOutSideCancel = false;
    private String confirmBtnTX = "确定", cancleBtnTX = "取消";
    private onDialogbtnClickListener btnClickListener;
    /**
     * 是否显示顶部图标
     */
    private boolean showTopIcon = true;
    /**
     * 进度框超时时间倍率
     */
    private int outOfTime = 10;
    private TextView dialogTitle, dialogMsg;
    // 弹出dialog时候是否要显示阴影
    private static boolean dimEnable = true;
    /**
     * 是否显示确认按钮
     */
    private boolean showConfirmBtn = true;
    private boolean progressTimeOutLimit = true;
    /**
     * 进度条颜色值数组 size =7
     */
    private int [] progressColors;

    /**
     * 构造器一 创建一个基本dialog
     *
     * @param context
     */
    public ComDialogBuilder(Context context) {
        this(context, DIALOG_STYLE_NORMAL);
    }

    // 重构构造函数 方便用户使用内部类监听器时使用

    /**
     * 构造器二
     *
     * @param context     上下文
     * @param layoutStyle 对话框风格
     */
    public ComDialogBuilder(Context context, int layoutStyle) {
        this(context, layoutStyle, false);
    }

    /**
     * 构造器三
     *
     * @param context
     * @param layoutStyle   布局样式
     * @param isSystemAlert 是否是系统弹框（service等地方用到系统级别不依赖activity）
     */
    public ComDialogBuilder(Context context, int layoutStyle,
                            boolean isSystemAlert) {
        this(context, layoutStyle, isSystemAlert, WIDTHFACTOR, ALPHAFACTOR, dimEnable);
    }

    /**
     * 构造器三
     *
     * @param context
     * @param layoutStyle   布局样式
     * @param isSystemAlert 是否是系统弹框（service等地方用到系统级别不依赖activity）
     */
    public ComDialogBuilder(Context context, int layoutStyle,
                            boolean isSystemAlert, boolean dimEnable) {
        this(context, layoutStyle, isSystemAlert, WIDTHFACTOR, ALPHAFACTOR, dimEnable);
    }

    /**
     * @param context
     * @param layoutStyle
     * @param widthcoefficient 宽度屏占比
     * @param dimEnable        是否显示阴影
     */
    public ComDialogBuilder(Context context, int layoutStyle,
                            float widthcoefficient, boolean dimEnable) {
        this(context, layoutStyle, false, widthcoefficient, ALPHAFACTOR, dimEnable);
    }

    /**
     * 构造器四
     *
     * @param context          上下文
     * @param layoutStyle      对话框布局样式
     * @param widthcoefficient 对话框宽度时占屏幕宽度的比重（0-1）
     */
    public ComDialogBuilder(Context context, int layoutStyle,
                            float widthcoefficient) {
        this(context, layoutStyle, false, widthcoefficient, ALPHAFACTOR, dimEnable);
    }

    /**
     * 对话框生成器五
     *
     * @param context          上下文
     * @param layoutStyle      样式
     * @param widthcoefficient 对话框宽度所占屏幕宽度的比重（0-1）
     * @param alpha            对话框透明度
     */
    public ComDialogBuilder(Context context, int layoutStyle,
                            float widthcoefficient, float alpha) {
        this(context, layoutStyle, false, widthcoefficient, alpha, dimEnable);
    }

    /**
     * 构造器
     *
     * @param context
     * @param layoutStyle      布局样式
     * @param isSystemAlert    是否是系统弹框（service等地方用到系统级别不依赖activity）
     * @param widthcoefficient 对话框宽度所占屏幕宽度的比重（0-1）
     * @param alpha            对话框透明度
     */
    public ComDialogBuilder(Context context, int layoutStyle,
                            boolean isSystemAlert, float widthcoefficient, float alpha, boolean dimEnable) {
        this.DIALOG_STYLE_CURRENT = layoutStyle;
        // theme 要传入一个样式去掉系统对话框的标题
        Dialog dialog = null;
        if (dimEnable) {
            dialog = new Dialog(context, R.style.Dialog);
        } else {
            dialog = new Dialog(context, R.style.DialogDim);
        }
        // 设置对话框风格
        dialog.setContentView(layoutStyle);
        Window window = dialog.getWindow();
        // 是否系统级弹框
        if (isSystemAlert) {
            window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        // 获取屏幕宽度
        DisplayMetrics metrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenwidth = metrics.widthPixels;
        int width = 0;
        if (widthcoefficient > 0) {
            width = (int) (screenwidth * widthcoefficient);
        } else {
            width = (int) (screenwidth * WIDTHFACTOR);
        }
        // 设置对话框宽度
        window.getAttributes().width = width;

        // 设置透明
        WindowManager.LayoutParams lp = window.getAttributes();
        if (alpha > 0) {
            lp.alpha = 1.0f;
        } else {
            lp.alpha = ALPHAFACTOR;
        }
        window.setAttributes(lp);
        // 设置动画样式
        window.setWindowAnimations(DIALOG_ANIM_NORMAL);
        this.context = context;
        this.dialog = dialog;
        dialogRootLayout = getView(R.id.cb_dialog_root_layout);
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        if (cancelBtn == null) {
            cancelBtn =  getView(R.id.dialog_neg_btn);
        }
    }

    /**
     * 创建对话框
     *
     * @return
     */
    public Dialog create() {
        if (confrimBtn == null) {
            confrimBtn =  getView(R.id.dialog_posi_btn);
        }
        if (showConfirmBtn) {
            confrimBtn.setVisibility(View.VISIBLE);
        } else {
            confrimBtn.setVisibility(View.GONE);
            LinearLayout btnLayout = getView(R.id.dialog_btnlayout);
            if (btnLayout != null) {
                btnLayout.setVisibility(View.GONE);
            }
        }
        if (cancelBtn == null) {
            cancelBtn =  getView(R.id.dialog_neg_btn);
        }
        if (verticleLine == null) {
            verticleLine = getView(R.id.btn_line_verticle);
        }
        if(horizatonalLine==null){
            horizatonalLine =getView(R.id.dialog_btn_line_horizontal);
        }
        if(showCancelButton){
            if(cancelBtn!=null){
                cancelBtn.setVisibility(View.VISIBLE);
            }
            if(verticleLine!=null){
                verticleLine.setVisibility(View.VISIBLE);
            }
            if(confrimBtn!=null&&cancelBtn!=null){
                confrimBtn.setBackgroundResource(confirmBtnBG>0?confirmBtnBG:R.drawable.cb_button_background_right);
            }
        }else{
            if(confrimBtn!=null){
                confrimBtn.setBackgroundResource(confirmBtnBG>0?confirmBtnBG:R.drawable.cb_button_background);
            }
        }

        if (confrimBtn != null) {
            confrimBtn.setText(confirmBtnTX);
            if (btnClickListener == null) {
                confrimBtn.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        }
        if (cancelBtn != null) {
            cancelBtn.setText(cancleBtnTX);
            if (btnClickListener == null) {
                cancelBtn.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        }

        if(context instanceof Activity){
            dialog.setOwnerActivity((Activity) context);
        }
        return dialog;
    }


    /**
     * 设置进度条颜色数组
     * @param colors
     * @return
     */
    public ComDialogBuilder setProgressStyleColorRes(int[]colors) {
        this.progressColors = colors;
        return this;
    }

    /**
     * set weather show cancel button, if true,the Dialog show two buttons
     *
     * @param showCancelButton
     * @return
     */
    public ComDialogBuilder showCancelButton(boolean showCancelButton) {
        this.showCancelButton = showCancelButton;
        return this;
    }

    public ComDialogBuilder showConfirmButton(boolean showConfirmbtn) {
        this.showConfirmBtn = showConfirmbtn;
        return this;
    }

//    /**
//     * 设置对话框标题
//     *
//     * @param title
//     * @return
//     */
//    public ComDialogBuilder setTitle(Object title) {
//        dialogTitle = getView(R.id.dialog_title);
//        if (dialogTitle != null) {
//            if (title != null) {
//                dialogTitle.setText(parseParam(title));
//            } else {
//                dialogTitle.setVisibility(View.GONE);
//            }
//        }
//        return this;
//    }

    /**
     * 给对话框设置动画
     *
     * @param resId
     */
    public ComDialogBuilder setDialogAnimation(int resId) {
        this.dialog.getWindow().setWindowAnimations(resId);
        return this;
    }

    /**
     * 设置对话框的位置
     *
     * @param location
     * @return
     */
    public ComDialogBuilder setDialoglocation(int location) {
        Window window = this.dialog.getWindow();
        switch (location) {
            case DIALOG_LOCATION_CENTER:
                window.setGravity(Gravity.CENTER);
                break;
            case DIALOG_LOCATION_BOTTOM:
                window.setGravity(Gravity.BOTTOM);
                break;
            case DIALOG_LOCATION_TOP:
                window.setGravity(Gravity.TOP);
                break;
            default:
                break;
        }
        return this;
    }


    /**
     * 设置对话框的标题内容
     *
     * @param message
     * @return
     */
    public ComDialogBuilder setTitleMessage(Object message) {
        dialogTitle = getView(R.id.dialog_message_title);
        if (dialogTitle != null) {
            if (message != null) {
                dialogTitle.setText(parseParam(message));
            } else {
                dialogTitle.setVisibility(View.GONE);
            }
        }
        return this;
    }



    /**
     * 设置对话框的消息内容
     *
     * @param message
     * @return
     */
    public ComDialogBuilder setMessage(Object message) {
        dialogMsg = getView(R.id.dialog_message);
        if (dialogMsg != null) {
            if (message != null) {
                dialogMsg.setText(parseParam(message));
            } else {
                dialogMsg.setVisibility(View.GONE);
            }
        }
        return this;
    }

    /**
     * 设置消息在对话框中的位置 MSG_LAYOUT_LEFT 居左 MSG_LAYOUT_CENTER 居中 默认居中
     *
     * @param layout
     * @return
     */
    public ComDialogBuilder setMessageGravity(int layout) {
        TextView dialogcontent = getView(R.id.dialog_message);
        if (dialogcontent != null && layout > 0) {
            if (layout == MSG_LAYOUT_LEFT) {
                dialogcontent.setGravity(Gravity.LEFT);
            } else if (layout == MSG_LAYOUT_CENTER) {
                dialogcontent.setGravity(Gravity.CENTER);
            }
        }
        return this;
    }

    /**
     * 给按钮设置回调监听
     *
     * @param btnClickListener 按钮的回调监听
     * @param isDissmiss       点击按钮后是否取消对话框
     * @return
     */
    public ComDialogBuilder setButtonClickListener(final boolean isDissmiss,
                                                  final onDialogbtnClickListener btnClickListener) {
//        if (DIALOG_STYLE_CURRENT != DIALOG_STYLE_NORMAL) {
//            return this;
//        }
        this.btnClickListener = btnClickListener;
        // 设置确认按钮
        final Button btnConfirm = getView(R.id.dialog_posi_btn);

        // 给按钮绑定监听器
        if(btnConfirm!=null){

            btnConfirm.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (isDissmiss) {
                        dialog.dismiss();
                    }
                    if (btnClickListener != null) {
                        btnClickListener.onDialogbtnClick(context, dialog,
                                onDialogbtnClickListener.BUTTON_CONFIRM);
                    }
                }
            });
        }

        // 设置消极按钮
        final Button btnCancel = getView(R.id.dialog_neg_btn);
        if(btnCancel!=null){
            btnCancel.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isDissmiss) {
                        dialog.dismiss();
                    }
                    if (btnClickListener != null) {
                        btnClickListener.onDialogbtnClick(context, dialog,
                                onDialogbtnClickListener.BUTTON_CANCEL);
                    }
                }
            });
        }
        return this;
    }

    /**
     * 给对话框中间内容设置为一个自定义view
     *
     * @param v
     * @return
     */
    public ComDialogBuilder setView(View v) {
        msglayout = getView(R.id.dialog_msg_layout);
        if (msglayout != null) {
            // 删除原来的textview
            msglayout.removeAllViews();
            // 添加新的view
            msglayout.addView(v);
        }
        return this;
    }

    /**
     * 根据用户传入的布局文件加载view到对话框
     *
     * @param nameInput
     * @return
     */
    public ComDialogBuilder setView(int nameInput) {
        ViewGroup msglayout = getView(R.id.dialog_msg_layout);
        // 需要传入添加的布局文件的父控件，false表示不需要inflate方法添加到父控件下，让我们自己添加
        return setView(LayoutInflater.from(context).inflate(nameInput,
                msglayout, false));
    }


    /**
     * set wheather dialog cancelable when touch outside
     *
     * @return
     */
    public ComDialogBuilder setTouchOutSideCancelable(boolean touchOutSideCancel) {
        this.touchOutSideCancel = touchOutSideCancel;
        if (DIALOG_STYLE_CURRENT == DIALOG_STYLE_NORMAL) {
            this.dialog.setCanceledOnTouchOutside(touchOutSideCancel);
        }
        return this;
    }

    /**
     * set the dialog wheather can cancelable;
     *
     * @param cancleable
     * @return
     */
    public ComDialogBuilder setCancelable(boolean cancleable) {
        this.dialog.setCancelable(cancleable);
        return this;
    }

    /**
     * 设置确定按钮文字
     *
     * @param confrim
     * @return
     */
    public ComDialogBuilder setConfirmButtonText(Object confrim) {
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        this.confirmBtnTX = getString(confrim);
        return this;
    }

    /**
     * 设置按钮文字大小
     * @param textSizeSP  unit sp
     * @return
     */
    public ComDialogBuilder setConfirmButtonTextSize(int textSizeSP) {
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        if(this.confrimBtn!=null){
            this.confrimBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSizeSP);
        }
        return this;
    }

    private String getString(Object confrim) {
        if (confrim == null) {
            return this.confirmBtnTX;
        }
        if (confrim instanceof String) {
            return (String) confrim;
        }
        if (confrim instanceof Integer) {
            return context.getString((Integer) confrim);
        }
        return this.confirmBtnTX;
    }

    /**
     * 设置取消按钮文字
     *
     * @param cancelTx
     * @return
     */
    public ComDialogBuilder setCancelButtonText(Object cancelTx) {
        if (cancelBtn == null) {
            cancelBtn =  getView(R.id.dialog_neg_btn);
        }
        this.cancleBtnTX = getString(cancelTx);
        return this;
    }

    /**
     * 设置按钮文字大小
     * @param textSizeSP  unit sp
     * @return
     */
    public ComDialogBuilder setCancelButtonTextSize(int textSizeSP) {
        if (cancelBtn == null) {
            cancelBtn =  getView(R.id.dialog_neg_btn);
        }
        if(this.cancelBtn!=null){
            this.cancelBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSizeSP);
        }
        return this;
    }


    /**
     * 根据子控件ID得到子控件
     *
     * @param id 子控件ID
     * @return 返回子控件
     */
    public <T extends View> T getView(int id) {

        return (T) dialog.findViewById(id);
    }

    /**
     * 解析用户传入的数据，字符串或者资源ID
     *
     * @return
     */
    private String parseParam(Object param) {
        // 如果是资源id 就通过上下文 获取资源
        if (param instanceof Integer) {
            return context.getString((Integer) param);
        } else if (param instanceof String) {
            return param.toString();
        }
        return null;

    }

    /**
     * set Confirm Button TextColor
     * @param color
     * @return
     */
    public ComDialogBuilder setConfirmButtonTextColor(int color) {
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        if(this.confrimBtn!=null){
            this.confrimBtn.setTextColor(color);
        }
        return this;
    }
    /**
     * set Cancel Button TextColor
     * @param color
     * @return
     */
    public ComDialogBuilder setCancelButtonTextColor(int color) {
        if (cancelBtn == null) {
            cancelBtn = getView(R.id.dialog_neg_btn);
        }
        if(this.cancelBtn!=null){
            this.cancelBtn.setTextColor(color);
        }
        return this;
    }

    /**
     * set message text size
     * @param textsize unit sp
     * @return
     */
    public ComDialogBuilder setMessageTextSize(int textsize) {
        if(dialogMsg!=null){
            dialogMsg.setTextSize(TypedValue.COMPLEX_UNIT_SP,textsize);
        }
        return this;
    }
    /**
     * set message text size
     * @param color  colorvalue
     * @return
     */
    public ComDialogBuilder setMessageTextColor(int color) {
        if(dialogMsg!=null){
            dialogMsg.setTextColor(color);
        }
        return this;
    }
    /**
     * set title text size
     * @param textsize unit sp
     * @return
     */
    public ComDialogBuilder setTitleTextSize(int textsize) {
        if(dialogTitle!=null){
            dialogTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP,textsize);
        }
        return this;
    }
    /**
     * set title text size
     * @param color  colorvalue
     * @return
     */
    public ComDialogBuilder setTitleTextColor(int color) {
        if(dialogTitle!=null){
            dialogTitle.setTextColor(color);
        }
        return this;
    }

    public ComDialogBuilder setDialogBackground(int ResID) {
        if(dialogRootLayout!=null){
            dialogRootLayout.setBackgroundResource(ResID);
        }
        return this;
    }


    /**
     * 监听器监听对话框按钮点击
     *
     * @author zhl
     */
    public interface onDialogbtnClickListener {
        /**
         * （区分点击的事左边按钮还是右边按钮）--确认
         */
        public static final int BUTTON_CONFIRM = 0;
        /**
         * （区分点击的事左边按钮还是右边按钮）--取消
         */
        public static final int BUTTON_CANCEL = 1;

        /**
         * @param context  上下文
         * @param dialog   点击的哪个对话框
         * @param whichBtn 点击的哪个按钮
         */
        void onDialogbtnClick(Context context, Dialog dialog, int whichBtn);

    }


    /**
     * set positive btn BackgroundResouce
     * @param resID
     * @return
     */
    public ComDialogBuilder setConfirmBackgroundResouce(int resID) {
        if (confrimBtn == null) {
            confrimBtn = getView(R.id.dialog_posi_btn);
        }
        if (confrimBtn != null && resID != -1) {
            confirmBtnBG = resID;
            confrimBtn.setBackgroundResource(resID);
        }
        return this;
    }

    /**
     * set negative btn BackgroundResouce
     * @param resID
     * @return
     */
    public ComDialogBuilder setCancelBackgroundResouce(int resID) {
        if (cancelBtn == null) {
            cancelBtn = getView(R.id.dialog_neg_btn);
        }
        if (cancelBtn != null && resID != -1) {
            cancelBtnBG = resID;
            cancelBtn.setBackgroundResource(resID);
        }
        return this;
    }

    /**
     * set progress style dialog has timeout limit
     * @param TimeOutLimit
     * @return
     */
    public ComDialogBuilder setProgressTimeOutLimit(boolean TimeOutLimit){
        this.progressTimeOutLimit = TimeOutLimit;
        return this;
    }


}
