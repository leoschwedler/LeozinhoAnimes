import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leozinhoanimes.databinding.ItemRvBinding
import com.example.leozinhoanimes.model.DateAnimes
import com.squareup.picasso.Picasso

class AnimesAdapter(
    private val onClick: (DateAnimes) -> Unit
) : RecyclerView.Adapter<AnimesAdapter.AnimesViewHolder>() {

    private val listAnimes = mutableListOf<DateAnimes>()
    private var filteredList = mutableListOf<DateAnimes>()

    fun addList(lista: List<DateAnimes>) {
        listAnimes.addAll(lista)
        notifyDataSetChanged()
        filteredList.addAll(lista)
    }

    fun filter(query: String) {
        filteredList.clear()
        filteredList.addAll(
            listAnimes.filter { it.title.contains(query, ignoreCase = true) }
        )
        notifyDataSetChanged()
    }

    inner class AnimesViewHolder(private val binding: ItemRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dateAnimes: DateAnimes) {
            with(binding) {
                val title = dateAnimes.title
                val urlImage = dateAnimes.images.jpg.image_url
                textTitle.text = title
                Picasso.get().load(urlImage).into(imgRv)
                imgRv.setOnClickListener {
                    onClick(dateAnimes)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimesViewHolder {
        val view =
            ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimesViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: AnimesViewHolder, position: Int) {
        val dateAnimes = filteredList[position]
        holder.bind(dateAnimes)
    }

}
