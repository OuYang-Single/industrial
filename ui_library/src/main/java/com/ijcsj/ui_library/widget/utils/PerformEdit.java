/*
 * Copyright 2016. SHENQINCI(沈钦赐)<946736079@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ijcsj.ui_library.widget.utils;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.ijcsj.ui_library.widget.WMEditText;

import java.util.Stack;


public class PerformEdit {
    //操作序号(一次编辑可能对应多个操作，如替换文字，就是删除+插入)
    int index;
    Stack<Action> history = new Stack<>();
    Stack<Action> historyBack = new Stack<>();

    private Editable editable;
    //撤销和重做操作标志，防止撤销时候，重复操作
    private boolean flag = false;

    public PerformEdit(@NonNull WMEditText editText) {
        CheckNull(editText, "EditText不能为空");
        editable = editText.getText();
        editText.addTextChangedListener(new Watcher());
    }

    protected void onEditableChanged(Editable s) {

    }

    protected void onTextChanged(Editable s) {

    }

    /**
     * 首次设置文本
     * Set default text.
     */
    public final void setDefaultText(CharSequence text){
        clearHistory();
        flag = true;
        editable.replace(0,editable.length(),text);
        flag = false;
    }

    /**
     * 清理记录
     * Clear history.
     */
    public final void clearHistory() {
        history.clear();
        historyBack.clear();
    }

    public boolean getUndoEmpty() {
        return history.empty();
    }
    public boolean getRedoEmpty() {
        return historyBack.empty();
    }
    /**
     * 撤销
     * Undo.
     */
    public final void undo() {
        if (history.empty()) return;
        //锁定操作
        flag = true;
        Action action = history.pop();
        historyBack.push(action);
        if (action.isAdd) {
            //撤销添加
            editable.delete(action.cursor, action.cursor + action.actionTarget.length());
        } else {
            //插销删除
            editable.insert(action.cursor, action.actionTarget);
        }
        //释放操作
        flag = false;
        //判断是否是下一个动作是否和本动作是同一个操作，直到不同为止
        if (!history.empty() && history.peek().index == action.index){
            undo();
        }
    }

    /**
     * 恢复
     * Redo.
     */
    public final void redo() {
        if (historyBack.empty()) return;
        flag = true;
        Action action = historyBack.pop();
        history.push(action);
        if (action.isAdd)//恢复添加
            editable.insert(action.cursor, action.actionTarget);
        else//恢复删除
            editable.delete(action.cursor, action.cursor + action.actionTarget.length());
        flag = false;
        //判断是否是下一个动作是否和本动作是同一个操作
        if (!historyBack.empty() && historyBack.peek().index == action.index)
            redo();
    }

    private class Watcher implements TextWatcher {

        /**
         * Before text changed.
         *
         * @param s     the s
         * @param start the start 起始光标
         * @param count the count 选择数量
         * @param after the after 替换增加的文字数
         */
        @Override
        public final void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (flag) return;
            int end = start + count;
            if (end > start && end <= s.length()) {
                CharSequence charSequence = s.subSequence(start, end);
                //发生文字变化
                if (charSequence.length() > 0) {
                    Action action = new Action(charSequence, start, false);
                    history.push(action);
                    historyBack.clear();
                    action.setIndex(++index);
                }
            }
        }

        /**
         * On text changed.
         *
         * @param s      the s
         * @param start  the start 起始光标
         * @param before the before 选择数量
         * @param count  the count 添加的数量
         */
        @Override
        public final void onTextChanged(CharSequence s, int start, int before, int count) {
            if (flag) return;
            int end = start + count;
            if (end > start) {
                CharSequence charSequence = s.subSequence(start, end);
                //发生文字变化
                if (charSequence.length() > 0) {
                    Action action = new Action(charSequence, start, true);
                    history.push(action);
                    historyBack.clear();
                    if (before > 0) {
                        //文字替换（先删除再增加），删除和增加是同一个操作，所以不需要增加序号
                        action.setIndex(index);
                    } else {
                        action.setIndex(++index);
                    }
                }
            }
        }

        @Override
        public final void afterTextChanged(Editable s) {
            if (flag) return;
            if (s != editable) {
                editable = s;
                onEditableChanged(s);
            }
            PerformEdit.this.onTextChanged(s);
        }

    }

    private class Action {
        /** 改变字符. */
        CharSequence actionTarget;
        /** 光标位置. */
        int cursor;
        /** 标志增加操作. */
        boolean isAdd;
        /** 操作序号. */
        int index;

        public Action(CharSequence actionTag, int cursor, boolean add) {
            this.actionTarget = actionTag;
            this.cursor = cursor;
            this.isAdd = add;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }


    private static void CheckNull(Object o,String message) {
        if(o == null)throw new IllegalStateException(message);
    }
}
