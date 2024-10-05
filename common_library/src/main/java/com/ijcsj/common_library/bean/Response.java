/**
  * Copyright 2022 bejson.com 
  */
package com.ijcsj.common_library.bean;
import java.util.List;

/**
 * Auto-generated: 2022-10-13 4:6:28
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Response {
    private Entity Error;
    private List<Results> Results;
    private int FaceNum;
    private String FaceModelVersion;
    private String RequestId;
    public void setResults(List<Results> Results) {
         this.Results = Results;
     }
     public List<Results> getResults() {
         return Results;
     }

    public void setFaceNum(int FaceNum) {
         this.FaceNum = FaceNum;
     }
     public int getFaceNum() {
         return FaceNum;
     }

    public void setFaceModelVersion(String FaceModelVersion) {
         this.FaceModelVersion = FaceModelVersion;
     }
     public String getFaceModelVersion() {
         return FaceModelVersion;
     }

    public void setRequestId(String RequestId) {
         this.RequestId = RequestId;
     }
     public String getRequestId() {
         return RequestId;
     }

    public Entity getError() {
        return Error;
    }

    public void setError(Entity error) {
        Error = error;
    }
}