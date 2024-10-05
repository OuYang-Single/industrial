package com.ijcsj.common_library.cos;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.ijcsj.common_library.bean.DownloadInit;
import com.ijcsj.common_library.util.Constant;
import com.ijcsj.ui_library.utils.AppGlobals;
import com.tencent.cos.xml.CosXmlService;
import com.tencent.cos.xml.CosXmlServiceConfig;
import com.tencent.cos.xml.exception.CosXmlClientException;
import com.tencent.cos.xml.exception.CosXmlServiceException;
import com.tencent.cos.xml.listener.CosXmlProgressListener;
import com.tencent.cos.xml.listener.CosXmlResultListener;
import com.tencent.cos.xml.model.CosXmlRequest;
import com.tencent.cos.xml.model.CosXmlResult;
import com.tencent.cos.xml.transfer.COSXMLDownloadTask;
import com.tencent.cos.xml.transfer.TransferConfig;
import com.tencent.cos.xml.transfer.TransferManager;
import com.tencent.cos.xml.transfer.TransferState;
import com.tencent.cos.xml.transfer.TransferStateListener;

public class CosXmlServiceInit {
    public static CosXmlServiceInit cosXmlServiceInit;
   private CosXmlService cosXmlService;
    DownloadInit downloadInit;
     Context context;
   public static CosXmlServiceInit init(DownloadInit downloadInit){
       if (cosXmlServiceInit==null){
         //  this.context=AppGlobals.INSTANCE.get();
           cosXmlServiceInit=    new CosXmlServiceInit(downloadInit);
       }
       if (System.currentTimeMillis()>downloadInit.getExpiredTime()){
           cosXmlServiceInit=    new CosXmlServiceInit(downloadInit);
       }
       return cosXmlServiceInit;
    }
    private  CosXmlServiceInit( DownloadInit downloadInit) {
        this.downloadInit=downloadInit;
        CosXmlServiceConfig serviceConfig = new CosXmlServiceConfig.Builder()
                .setRegion(downloadInit.getRegion())
                .isHttps(true) // 使用 HTTPS 请求，默认为 HTTP 请求
                .builder();
        this.context=AppGlobals.INSTANCE.get();
        cosXmlService = new CosXmlService(context, serviceConfig, new MySessionCredentialProvider(downloadInit.getCre().getTmpSecretId(),downloadInit.getCre().getTmpSecretKey(),downloadInit.getCre().getSessionToken(),
                downloadInit.getExpiredTime(),downloadInit. getStartTime()
        ));
    }

    public CosXmlService getCosXmlService() {
        return cosXmlService;
    }


    public void transferDownloadObject() {
        //.cssg-snippet-body-start:[transfer-download-object]
        // 高级下载接口支持断点续传，所以会在下载前先发起 HEAD 请求获取文件信息。
        // 如果您使用的是临时密钥或者使用子账号访问，请确保权限列表中包含 HeadObject 的权限。

        // 初始化 TransferConfig，这里使用默认配置，如果需要定制，请参考 SDK 接口文档
        TransferConfig transferConfig = new TransferConfig.Builder().build();
        //初始化 TransferManager
        TransferManager transferManager = new TransferManager(cosXmlService,
                transferConfig);

        // 存储桶名称，由bucketname-appid 组成，appid必须填入，可以在COS控制台查看存储桶名称。 https://console.cloud.tencent.com/cos5/bucket
        String bucket = downloadInit.getBucket();
        String cosPath = "exampleobject"; //对象在存储桶中的位置标识符，即称对象键
        //本地目录路径
        String savePathDir = context.getExternalCacheDir().toString();
        //本地保存的文件名，若不填（null），则与 COS 上的文件名一样
        Context applicationContext = context.getApplicationContext(); // application
        // context
        COSXMLDownloadTask cosxmlDownloadTask =
                transferManager.download(applicationContext,
                        bucket, cosPath, savePathDir, null);

        //设置下载进度回调
        cosxmlDownloadTask.setCosXmlProgressListener(new CosXmlProgressListener() {
            @Override
            public void onProgress(long complete, long target) {
                Log.e("","");

            }
        });
        //设置返回结果回调
        cosxmlDownloadTask.setCosXmlResultListener(new CosXmlResultListener() {
            @Override
            public void onSuccess(CosXmlRequest request, CosXmlResult result) {
                COSXMLDownloadTask.COSXMLDownloadTaskResult downloadTaskResult =
                        (COSXMLDownloadTask.COSXMLDownloadTaskResult) result;
            }

            // 如果您使用 kotlin 语言来调用，请注意回调方法中的异常是可空的，否则不会回调 onFail 方法，即：
            // clientException 的类型为 CosXmlClientException?，serviceException 的类型为 CosXmlServiceException?
            @Override
            public void onFail(CosXmlRequest request,
                               @Nullable CosXmlClientException clientException,
                               @Nullable CosXmlServiceException serviceException) {
                if (clientException != null) {
                    clientException.printStackTrace();
                } else {
                    serviceException.printStackTrace();
                }
            }
        });
        //设置任务状态回调，可以查看任务过程
        cosxmlDownloadTask.setTransferStateListener(new TransferStateListener() {
            @Override
            public void onStateChanged(TransferState state) {

            }
        });
        //.cssg-snippet-body-end
    }

   /* *//**
     * 下载暂停、续传与取消
     *//*
    private void transferDownloadObjectInteract() {

        // 初始化 TransferConfig，这里使用默认配置，如果需要定制，请参考 SDK 接口文档
        TransferConfig transferConfig = new TransferConfig.Builder().build();
        //初始化 TransferManager
        TransferManager transferManager = new TransferManager(cosXmlService,
                transferConfig);

        // 存储桶名称，由bucketname-appid 组成，appid必须填入，可以在COS控制台查看存储桶名称。 https://console.cloud.tencent.com/cos5/bucket
        String bucket = "examplebucket-1250000000";
        String cosPath = "exampleobject"; //对象在存储桶中的位置标识符，即称对象键
        //本地目录路径
        String savePathDir = context.getExternalCacheDir().toString();
        //本地保存的文件名，若不填（null），则与 COS 上的文件名一样
        String savedFileName = "exampleobject";

        Context applicationContext = context.getApplicationContext(); // application
        // context
        COSXMLDownloadTask cosxmlDownloadTask =
                transferManager.download(applicationContext,
                        bucket, cosPath, savePathDir, savedFileName);

        //.cssg-snippet-body-start:[transfer-download-object-pause]
        cosxmlDownloadTask.pause();;
        //.cssg-snippet-body-end

        //.cssg-snippet-body-start:[transfer-download-object-resume]
        cosxmlDownloadTask.resume();
        //.cssg-snippet-body-end

        //.cssg-snippet-body-start:[transfer-download-object-cancel]
        cosxmlDownloadTask.cancel();
        //.cssg-snippet-body-end
    }*/

}
