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
public class Results {

    private int RetCode;
    private List<Candidates> Candidates;
    private FaceRect FaceRect;
    public void setRetCode(int RetCode) {
         this.RetCode = RetCode;
     }
     public int getRetCode() {
         return RetCode;
     }

    public void setCandidates(List<Candidates> Candidates) {
         this.Candidates = Candidates;
     }
     public List<Candidates> getCandidates() {
         return Candidates;
     }

    public void setFaceRect(FaceRect FaceRect) {
         this.FaceRect = FaceRect;
     }
     public FaceRect getFaceRect() {
         return FaceRect;
     }

}