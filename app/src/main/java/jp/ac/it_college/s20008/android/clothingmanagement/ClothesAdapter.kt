package jp.ac.it_college.s20008.android.clothingmanagement

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class ClothesAdapter(val context: Context, val clothesList: List<Clothes>) :
        RecyclerView.Adapter<ClothesAdapter.ViewHolder>() {
    init {
        setHasStableIds(true)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val clothesImage:ImageView = view.findViewById(R.id.clothesImage)
        val clothesName: TextView = view.findViewById(R.id.clothesName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.clothes_item, parent, false)
//        return ViewHolder(view)
        val holder = ViewHolder(view)

        holder.itemView.setOnClickListener{
            val position = holder.bindingAdapterPosition
            val clothes = clothesList[position]
            val intent = Intent(context, ClothesActivity::class.java).apply {
                putExtra(ClothesActivity.CLOTHES_IMAGE_ID, clothes.name)
            }
            context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ClothesAdapter.ViewHolder, position: Int) {
        val clothes = clothesList[position]
//        val clothess: ClothesData? = getItem(position)
        holder.clothesName.text = clothes.name
        Glide.with(context).load(clothes.imageId).into(holder.clothesImage)

//        holder.itemView.setOnClickListener {
//            listener?.invoke(clothess?.id)
//        }

    }

    override fun getItemCount() = clothesList.size

}