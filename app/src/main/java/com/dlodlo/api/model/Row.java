package com.dlodlo.api.model;



public class Row{

    private static final String FIELD_VERSION_CODE = "versionCode";
    private static final String FIELD_DOWN_NUM = "downNum";
    private static final String FIELD_PRAISE_NUM = "praiseNum";
    private static final String FIELD_ID = "id";
    private static final String FIELD_VERSION_NAME = "versionName";
    private static final String FIELD_STATE = "state";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_UPDATE_TIME = "updateTime";
    private static final String FIELD_FILE_SIZE = "fileSize";
    private static final String FIELD_ICON_URL = "iconUrl";
    private static final String FIELD_IMG_URLS = "imgUrls";
    private static final String FIELD_URL_LIST = "urlList";
    private static final String FIELD_DOWN_URL = "downUrl";
    private static final String FIELD_PACKAGE_NAME = "packageName";
    private static final String FIELD_CREATE_TIME = "createTime";
    private static final String FIELD_RESOURCE_TYPE = "resourceType";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_MARK = "mark";


    private VersionCode mVersionCode;
    private DownNum mDownNum;
    private PraiseNum mPraiseNum;
    private Id mId;
    private VersionName mVersionName;
    private State mState;
    private Description mDescription;
    private UpdateTime mUpdateTime;
    private FileSize mFileSize;
    private IconUrl mIconUrl;
    private ImgUrl mImgUrl;
    private UrlList mUrlList;
    private DownUrl mDownUrl;
    private PackageName mPackageName;
    private CreateTime mCreateTime;
    private ResourceType mResourceType;
    private Name mName;
    private Mark mMark;


    public Row(){

    }

    public void setVersionCode(VersionCode versionCode) {
        mVersionCode = versionCode;
    }

    public VersionCode getVersionCode() {
        return mVersionCode;
    }

    public void setDownNum(DownNum downNum) {
        mDownNum = downNum;
    }

    public DownNum getDownNum() {
        return mDownNum;
    }

    public void setPraiseNum(PraiseNum praiseNum) {
        mPraiseNum = praiseNum;
    }

    public PraiseNum getPraiseNum() {
        return mPraiseNum;
    }

    public void setId(Id id) {
        mId = id;
    }

    public Id getId() {
        return mId;
    }

    public void setVersionName(VersionName versionName) {
        mVersionName = versionName;
    }

    public VersionName getVersionName() {
        return mVersionName;
    }

    public void setState(State state) {
        mState = state;
    }

    public State getState() {
        return mState;
    }

    public void setDescription(Description description) {
        mDescription = description;
    }

    public Description getDescription() {
        return mDescription;
    }

    public void setUpdateTime(UpdateTime updateTime) {
        mUpdateTime = updateTime;
    }

    public UpdateTime getUpdateTime() {
        return mUpdateTime;
    }

    public void setFileSize(FileSize fileSize) {
        mFileSize = fileSize;
    }

    public FileSize getFileSize() {
        return mFileSize;
    }

    public void setIconUrl(IconUrl iconUrl) {
        mIconUrl = iconUrl;
    }

    public IconUrl getIconUrl() {
        return mIconUrl;
    }

    public void setImgUrl(ImgUrl imgUrl) {
        mImgUrl = imgUrl;
    }

    public ImgUrl getImgUrl() {
        return mImgUrl;
    }

    public void setUrlList(UrlList urlList) {
        mUrlList = urlList;
    }

    public UrlList getUrlList() {
        return mUrlList;
    }

    public void setDownUrl(DownUrl downUrl) {
        mDownUrl = downUrl;
    }

    public DownUrl getDownUrl() {
        return mDownUrl;
    }

    public void setPackageName(PackageName packageName) {
        mPackageName = packageName;
    }

    public PackageName getPackageName() {
        return mPackageName;
    }

    public void setCreateTime(CreateTime createTime) {
        mCreateTime = createTime;
    }

    public CreateTime getCreateTime() {
        return mCreateTime;
    }

    public void setResourceType(ResourceType resourceType) {
        mResourceType = resourceType;
    }

    public ResourceType getResourceType() {
        return mResourceType;
    }

    public void setName(Name name) {
        mName = name;
    }

    public Name getName() {
        return mName;
    }

    public void setMark(Mark mark) {
        mMark = mark;
    }

    public Mark getMark() {
        return mMark;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Row){
        		return ((Row) obj).getId().equals(mId);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return mId.hashCode();
    }


}