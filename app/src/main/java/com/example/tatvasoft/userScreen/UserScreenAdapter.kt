package com.example.tatvasoft.userScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tatvasoft.R
import com.example.tatvasoft.models.User
import kotlinx.android.synthetic.main.item_user.view.*


class UserScreenAdapter(
    private var items: MutableList<User> = mutableListOf(),
    private val context: Context,
) : RecyclerView.Adapter<UserScreenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.oddLayout.visibility = View.GONE
        holder.parentLayout.removeAllViews()

        Glide.with(context)
            .load(item.image)
            .into(holder.pic)

        holder.name.text = item.name

        /**
         * check item's even or odd
         */
        if((item.items.size % 2) != 0){
            holder.oddLayout.visibility = View.VISIBLE

            if((item.items.size / 2) != 0){
                Glide.with(context)
                    .load(item.items[0])
                    .into(holder.oddLayout)
                holder.addLayout(item, (item.items.size / 2),true)
            }
            else{
                Glide.with(context)
                    .load(item.items[0])
                    .into(holder.oddLayout)
            }
        }
        else{
            holder.oddLayout.visibility = View.GONE
            holder.addLayout(item, (item.items.size / 2),false)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * usage set updated item in list
     * @param item
     * @return none
     */
    fun setItem(item: List<User>){
        items.addAll(item)
        notifyDataSetChanged()
    }

 inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val name = view.item_name!!
        val pic = view.item_profile_image!!
        val oddLayout = view.item_odd_first!!
        val parentLayout = view.parent_ly!!

     /**
      * add linear layout as per the data
      */

     fun addLayout(item: User, rem: Int, odd: Boolean) {

         for (i in  0 until rem) {
             val layout = LinearLayout(context)

             layout.layoutParams =
                 LinearLayout.LayoutParams(
                     ViewGroup.LayoutParams.MATCH_PARENT,
                     ViewGroup.LayoutParams.WRAP_CONTENT
                 )
             layout.orientation = LinearLayout.HORIZONTAL

             val firstParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                 ViewGroup.LayoutParams.MATCH_PARENT , 300
             )
             firstParams.weight = 0.5f
             firstParams.setMargins(0,10,10,10)

             val firstImage = AppCompatImageView(context)
             firstImage.layoutParams = firstParams
             firstImage.scaleType = ImageView.ScaleType.FIT_XY

             if(odd){
                 Glide.with(context)
                     .load(item.items[i]+1)
                     .into(firstImage)
             }
             else{
                 Glide.with(context)
                     .load(item.items[i])
                     .into(firstImage)
             }

             layout.addView(firstImage)

             val secondParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                 ViewGroup.LayoutParams.MATCH_PARENT, 300
             )
             secondParams.weight = 0.5f
             secondParams.setMargins(10,10,0,10)

             val secondImage = AppCompatImageView(context)
             secondImage.layoutParams = secondParams
             secondImage.scaleType = ImageView.ScaleType.FIT_XY

             if(odd){
                 Glide.with(context)
                     .load(item.items[i]+2)
                     .into(secondImage)
             }
             else{
                 Glide.with(context)
                     .load(item.items[i]+1)
                     .into(secondImage)
             }

             layout.addView(secondImage)

             parentLayout.addView(layout)
         }
     }
 }
}