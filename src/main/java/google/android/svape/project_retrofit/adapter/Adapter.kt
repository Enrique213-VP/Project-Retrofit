package google.android.svape.project_retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import google.android.svape.project_retrofit.R
import google.android.svape.project_retrofit.databinding.ListItemBinding
import google.android.svape.project_retrofit.model.fromUrl

class Adapter(private val images: List<String>): RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = images.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ListItemBinding.bind(view)

        fun bind(image: String) {
            binding.ImageDogs.fromUrl(image)
        }
    }


}