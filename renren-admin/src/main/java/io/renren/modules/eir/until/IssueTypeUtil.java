package io.renren.modules.eir.until;

public class IssueTypeUtil {

    public static String transition(String issueType){
        String issue = "";
        switch (issueType) {
            case "1":
                issue = "此街区没有机械作业";
                break;
            case "2":
                issue = "此街区机械作业繁忙";
                break;
            case "3":
                issue = "此街区机械不吊箱";
                break;
            case "4":
                issue = "提的柜号为烂柜";
                break;
            case "5":
                issue = "柜子吊箱门、没放好重新吊放";
                break;
            case "6":
                issue = "雪柜需要电工协助";
                break;
            case "7":
                issue = "完成作业后刷卡无法出闸";
                break;
            case "8":
                issue = "进场后无堆场位置";
                break;
            default:
                issue = "其他";
                break;
        }
        return issue;
    }

}
