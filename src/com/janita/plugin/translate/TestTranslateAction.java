package com.janita.plugin.translate;

import com.google.gson.Gson;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.janita.plugin.domain.TranslationResult;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import okhttp3.Call;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author zhucj
 */
public class TestTranslateAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getData(PlatformDataKeys.PROJECT);
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
        //调翻译接口
        Gson gson = new Gson();
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
                ApplicationManager.getApplication().invokeLater(() -> Messages.showErrorDialog(project, "请求翻译失败", "标题"));
            }

            @Override
            public void onResponse(String s, int i) {
                TranslationResult translationResult = gson.fromJson(s, TranslationResult.class);
                ApplicationManager.getApplication().invokeLater(() -> showPop(translationResult, editor));
            }
        });
    }

    /**
     * 显示气泡
     */
    private void showPop(TranslationResult translationResult, Editor editor) {
        //pop弹窗
        JBPopupFactory factory = JBPopupFactory.getInstance();
        BalloonBuilder builder = factory.createHtmlTextBalloonBuilder(translationResult.toString(),
                null, new JBColor(new Color(188, 238, 188),
                        new Color(73, 120, 73)), null);
        //        无操作5s隐藏,
        builder.createBalloon();
        builder.setFadeoutTime(5000L)
                .createBalloon()
                .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
    }

}
