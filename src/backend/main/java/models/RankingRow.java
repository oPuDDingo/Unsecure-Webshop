package backend.main.java.models;

public class RankingRow {

    private String ipAddress;
    private boolean sqlInjection;
    private boolean blindSqlInjection;
    private boolean emailWithoutAt;
    private boolean xss;
    private boolean profile_picture;
    private boolean htmlCommentUser;
    private boolean priceOrderBug;
    private boolean guessUserLogin;
    private boolean guessCoupon;
    private boolean deleteUser;
    private boolean commentXss;
    private boolean lookForEmailAddress;
    private boolean hashUser;

    public RankingRow() {
    }

    public RankingRow(String ipAddress, boolean sqlInjection, boolean blindSqlInjection, boolean emailWithoutAt, boolean xss, boolean profile_picture, boolean htmlCommentUser, boolean priceOrderBug, boolean guessUserLogin, boolean guessCoupon, boolean deleteUser, boolean commentXss, boolean lookForEmailAddress, boolean hashUser) {
        this.ipAddress = ipAddress;
        this.sqlInjection = sqlInjection;
        this.blindSqlInjection = blindSqlInjection;
        this.emailWithoutAt = emailWithoutAt;
        this.xss = xss;
        this.profile_picture = profile_picture;
        this.htmlCommentUser = htmlCommentUser;
        this.priceOrderBug = priceOrderBug;
        this.guessUserLogin = guessUserLogin;
        this.guessCoupon = guessCoupon;
        this.deleteUser = deleteUser;
        this.commentXss = commentXss;
        this.lookForEmailAddress = lookForEmailAddress;
        this.hashUser = hashUser;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isSqlInjection() {
        return sqlInjection;
    }

    public void setSqlInjection(boolean sqlInjection) {
        this.sqlInjection = sqlInjection;
    }

    public boolean isBlindSqlInjection() {
        return blindSqlInjection;
    }

    public void setBlindSqlInjection(boolean blindSqlInjection) {
        this.blindSqlInjection = blindSqlInjection;
    }

    public boolean isEmailWithoutAt() {
        return emailWithoutAt;
    }

    public void setEmailWithoutAt(boolean emailWithoutAt) {
        this.emailWithoutAt = emailWithoutAt;
    }

    public boolean isXss() {
        return xss;
    }

    public void setXss(boolean xss) {
        this.xss = xss;
    }

    public boolean isProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(boolean profile_picture) {
        this.profile_picture = profile_picture;
    }

    public boolean isHtmlCommentUser() {
        return htmlCommentUser;
    }

    public void setHtmlCommentUser(boolean htmlCommentUser) {
        this.htmlCommentUser = htmlCommentUser;
    }

    public boolean isPriceOrderBug() {
        return priceOrderBug;
    }

    public void setPriceOrderBug(boolean priceOrderBug) {
        this.priceOrderBug = priceOrderBug;
    }

    public boolean isGuessUserLogin() {
        return guessUserLogin;
    }

    public void setGuessUserLogin(boolean guessUserLogin) {
        this.guessUserLogin = guessUserLogin;
    }

    public boolean isGuessCoupon() {
        return guessCoupon;
    }

    public void setGuessCoupon(boolean guessCoupon) {
        this.guessCoupon = guessCoupon;
    }

    public boolean isDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(boolean deleteUser) {
        this.deleteUser = deleteUser;
    }

    public boolean isCommentXss() {
        return commentXss;
    }

    public void setCommentXss(boolean commentXss) {
        this.commentXss = commentXss;
    }

    public boolean isLookForEmailAddress() {
        return lookForEmailAddress;
    }

    public void setLookForEmailAddress(boolean lookForEmailAddress) {
        this.lookForEmailAddress = lookForEmailAddress;
    }

    public boolean isHashUser() {
        return hashUser;
    }

    public void setHashUser(boolean hashUser) {
        this.hashUser = hashUser;
    }
}
