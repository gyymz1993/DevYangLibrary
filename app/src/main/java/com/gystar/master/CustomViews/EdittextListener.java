package com.gystar.master.CustomViews;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by onetouch on 2017/11/22.
 */

public abstract class EdittextListener implements TextWatcher {

    EditText mInputEditText;
    private int maxLenght;

    public EdittextListener() {
    }

    public EdittextListener(EditText mInputEditText, int maxLenght) {
        this.mInputEditText = mInputEditText;
        this.maxLenght = maxLenght;
    }

    public EdittextListener(EditText mInputEditText) {
        this.mInputEditText = mInputEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        int end = editable.length();
        UnderlineSpan[] da =
                editable.getSpans(0, end, UnderlineSpan.class);
        if (da.length > 0) {
        } else {
            if (editable.length() > maxLenght) {
                mInputEditText.setText(editable.subSequence(0, maxLenght));
                mInputEditText.setSelection(mInputEditText.getText().length());
            }

        }

    }

    /**
     * refresh prompt content numbers
     */
    private void setPromptContent() {
        int contentLength = mInputEditText.getText().toString().length();
        mInputEditText.setSelection(contentLength);
        int limitedLength = maxLenght - contentLength;

    }

    /**
     * This filter will constrain edits not to make the length of the text
     * greater than the specified length.
     */
    public static class LengthFilter implements InputFilter {
        private final int mMax;

        //通过构造方法，将maxlength赋值给mMax
        public LengthFilter(int max) {
            mMax = max;
        }

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                   int dstart, int dend) {
            //这里的操作可能不太懂，但是这种格式可以简化一下，成为：
            //int keep = mMax - x;
            //可以猜想：keep表示当前字符超出maxLength的大小
            int keep = mMax - (dest.length() - (dend - dstart));

            //这里对keep的值分别作了判断:
            //keep<=0:也就是当前输入的字符数量大于或者等于maxLength
            //返回""，当字符数量达到maxLength时，我们不能再输入内容，""符合我们的认知
            if (keep <= 0) {
                //可以尝试在这里打印日志，看看是否符合我们的需求（这里运气比较好，一次实验就会达到目的，然后就可以封装成一开始给出的代码）
                return "";
            } else if (keep >= end - start) {
                return null; // keep original
            } else {
                keep += start;
                if (Character.isHighSurrogate(source.charAt(keep - 1))) {
                    --keep;
                    if (keep == start) {
                        return "";
                    }
                }
                return source.subSequence(start, keep);
            }
        }

        /**
         * @return the maximum length enforced by this input filter
         */
        public int getMax() {
            return mMax;
        }
    }


    public static class NameLengthFilter implements InputFilter {
        int MAX_EN;// 最大英文/数字长度 一个汉字算两个字母
        String regEx = "[\\u4e00-\\u9fa5]"; // unicode编码，判断是否为汉字

        public NameLengthFilter(int mAX_EN) {
            super();
            MAX_EN = mAX_EN;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            int destCount = dest.toString().length()
                    + getChineseCount(dest.toString());
            int sourceCount = source.toString().length()
                    + getChineseCount(source.toString());
            String name = "";
            int count = 0;
            int i = 0;
            if (destCount + sourceCount > MAX_EN) {
                if (destCount < MAX_EN) {
                    while (!(destCount + count >= MAX_EN)) {
                        ++i;
                        name = source.subSequence(0, i).toString();
                        count = name.toString().length()
                                + getChineseCount(name.toString());
                        if (destCount + count > MAX_EN) {
                            --i;
                        }
                    }
                    return i == 0 ? "" : source.subSequence(0, i).toString();
                }
                return "";
            } else {
                return source;
            }
        }

        private int getChineseCount(String str) {
            int count = 0;
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            while (m.find()) {
                //楼下的朋友提供更简洁的代谢
                count++;
            }
            return count;
        }
    }

}

