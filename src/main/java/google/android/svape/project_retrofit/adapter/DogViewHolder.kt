package google.android.svape.project_retrofit.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import google.android.svape.project_retrofit.databinding.ListItemBinding

class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ListItemBinding.bind(view)

    fun bind(image: String){
        Picasso.get().load(image).into(binding.ImageDogs)
    }
}