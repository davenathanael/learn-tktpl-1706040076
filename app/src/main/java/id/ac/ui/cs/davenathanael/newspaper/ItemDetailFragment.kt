package id.ac.ui.cs.davenathanael.newspaper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_ITEM_ID = "itemId"
private const val ARG_ITEM_CONTENT = "itemContent"
private const val ARG_ITEM_DETAILS = "itemDetails"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemDetailFragment : Fragment() {
    private var itemId: String? = null
    private var itemContent: String? = null
    private var itemDetails: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getString(ARG_ITEM_ID)
            itemContent = it.getString(ARG_ITEM_CONTENT)
            itemDetails = it.getString(ARG_ITEM_DETAILS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_detail, container, false)

        view.findViewById<TextView>(R.id.detail_item_id).apply {
            text = itemId
        }
        view.findViewById<TextView>(R.id.detail_item_content).apply {
            text = itemContent
        }
        view.findViewById<TextView>(R.id.detail_item_details).apply {
            text = itemDetails
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @param param3 Parameter 3.
         * @return A new instance of fragment ItemDetailFragment.
         */
        @JvmStatic
        fun newInstance(itemId: String, itemContent: String, itemDetails: String) =
            ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ITEM_ID, itemId)
                    putString(ARG_ITEM_CONTENT, itemContent)
                    putString(ARG_ITEM_DETAILS, itemDetails)
                }
            }
    }
}