package com.janita.plugin.translate;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhucj
 */
public class TestTranslateAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        //获取用户选择的内容
        //获取当前用户所在的编辑器对象
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        //通过编辑器得到用户选择的对象模型
        SelectionModel model = editor.getSelectionModel();
        //获取模型中的文本
        String selectedText = model.getSelectedText();
        if (StringUtils.isEmpty(selectedText)) {
            return;
        }
        System.out.println("选中的文本：" + selectedText + "，开始调翻译接口");

        //调翻译接口
        OkHttpUtils.post()
                .url("http://fanyi.youdao.com/openapi.do")
                .addParams("keyfrom", "HMTranslate")
                .addParams("key", "278683081")
                .addParams("type", "data")
                .addParams("doctype", "json")
                .addParams("version", "1.1")
                .addParams("q", selectedText)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                System.out.println("请求翻译失败");
            }

            @Override
            public void onResponse(String s, int i) {
                System.out.println("请求翻译成功：" + s);
            }
        });

        //展示

    }
}
