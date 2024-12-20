package com.ijcsj.common_library.cos;

import com.tencent.qcloud.core.auth.BasicLifecycleCredentialProvider;
import com.tencent.qcloud.core.auth.QCloudLifecycleCredentials;
import com.tencent.qcloud.core.auth.SessionQCloudCredentials;
import com.tencent.qcloud.core.common.QCloudClientException;

public  class MySessionCredentialProvider extends BasicLifecycleCredentialProvider {
 private    String tmpSecretId;
    private   String tmpSecretKey ;
    private   String sessionToken;
    private   long expiredTime;
    private   long startTime;
   public MySessionCredentialProvider( String tmpSecretId,  String tmpSecretKey ,String sessionToken,long expiredTime,  long startTime){
       this.tmpSecretId=tmpSecretId;
       this.tmpSecretKey=tmpSecretKey;
       this.sessionToken=sessionToken;
       this.expiredTime=expiredTime;
       this.startTime=startTime;

    }
    @Override
    protected QCloudLifecycleCredentials fetchNewCredentials()
            throws QCloudClientException {
        // 最后返回临时密钥信息对象
        return new SessionQCloudCredentials(tmpSecretId, tmpSecretKey,
                sessionToken, startTime, expiredTime);
    }
}