package com.janita.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

/**
 * @author zhucj
 */
public class HelloTestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        //获取到当前工程对象
        Project project = e.getData(PlatformDataKeys.PROJECT);
        //显示一个对话框，让用户输入名字
        System.out.println("显示对话框");
        String name = Messages.showInputDialog(project, "请输入您的名字", "请输入姓名", Messages.getQuestionIcon());
        System.out.println("得到名称 = " +name);
        //再弹出一个对话框，给用户打招呼
        Messages.showMessageDialog(project, "欢迎你：" + name, "标题", Messages.getInformationIcon());
    }
}
