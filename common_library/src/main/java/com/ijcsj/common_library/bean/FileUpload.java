package com.ijcsj.common_library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FileUpload  implements Parcelable {
    /**
     * 附件id
     */
    private Long attachInfoId;
    /**
     * 上传成功文件key
     */
    private String fileKey;
    /**
     * 上传成功文件url
     */
    private String fileUrl;

    public FileUpload(){

    }

    protected FileUpload(Parcel in) {
        if (in.readByte() == 0) {
            attachInfoId = null;
        } else {
            attachInfoId = in.readLong();
        }
        fileKey = in.readString();
        fileUrl = in.readString();
    }

    public static final Creator<FileUpload> CREATOR = new Creator<FileUpload>() {
        @Override
        public FileUpload createFromParcel(Parcel in) {
            return new FileUpload(in);
        }

        @Override
        public FileUpload[] newArray(int size) {
            return new FileUpload[size];
        }
    };

    public Long getAttachInfoId() {
        return attachInfoId;
    }

    public void setAttachInfoId(Long attachInfoId) {
        this.attachInfoId = attachInfoId;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        if (attachInfoId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(attachInfoId);
        }
        parcel.writeString(fileKey);
        parcel.writeString(fileUrl);
    }
}
