package com.ijcsj.ui_library.editor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.ijcsj.ui_library.R;
import com.ijcsj.ui_library.editor.bean.MaterialsMenuBean;
import com.ijcsj.ui_library.editor.interfaces.OnMaterialsItemClickListener;

import java.util.List;

/**
 * 素材菜单适配器
 *
 * Created by yyp on 2019.04.11
 */
public class MaterialsMenuAdapter extends RecyclerView.Adapter<MaterialsMenuAdapter.MaterialsHolder> {

    private List<MaterialsMenuBean> mData;
    private OnMaterialsItemClickListener mOnMaterialsItemClickListener;

    public MaterialsMenuAdapter(List<MaterialsMenuBean> mData) {
        this.mData = mData;
    }

    /**
     * 设置素材菜单item点击监听
     *
     * @param onMaterialsItemClickListener .
     */
    public void setOnMaterialsItemClickListener(OnMaterialsItemClickListener onMaterialsItemClickListener) {
        this.mOnMaterialsItemClickListener = onMaterialsItemClickListener;
    }

    @NonNull
    @Override
    public MaterialsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MaterialsHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.module_editor_item_materials, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialsHolder materialsHolder, int i) {
        final MaterialsMenuBean bean = mData.get(i);
        materialsHolder.mMaterialsImg.setImageResource(bean.getImgResId());
        materialsHolder.mMaterialsTitle.setText(bean.getTitle());
        materialsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnMaterialsItemClickListener != null){ //素材菜单点击回调
                    mOnMaterialsItemClickListener.onMaterialsItemClick(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }


    /**
     * 素材菜单holder
     */
    class MaterialsHolder extends RecyclerView.ViewHolder{

        ImageView mMaterialsImg;
        TextView mMaterialsTitle;

        MaterialsHolder(@NonNull View itemView) {
            super(itemView);
            mMaterialsImg = itemView.findViewById(R.id.iv_materials_img);
            mMaterialsTitle = itemView.findViewById(R.id.tv_materials_title);
        }
    }
}
