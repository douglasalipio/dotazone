package com.br.dotazone.model.service

import android.app.ProgressDialog
import android.os.AsyncTask
import android.util.Log
import androidx.fragment.app.Fragment
import com.br.dotazone.DotaZoneBrain.TAG
import com.br.dotazone.DotaZoneBrain.items
import com.br.dotazone.R
import com.br.dotazone.domain.heroes.prov.Item
import com.br.dotazone.domain.heroes.prov.Item.ItemElementy
import com.br.dotazone.domain.heroes.prov.ItemAtrrib
import com.br.dotazone.domain.heroes.prov.ItemAtrrib.ItemAttribElementy
import com.br.dotazone.model.util.UrlUtils.Companion.convertStreamToString
import com.br.dotazone.model.util.UrlUtils.Companion.getUrlItem
import com.br.dotazone.view.activity.BaseActivity
import com.br.dotazone.view.fragment.DialogError.Companion.newFragmentDialog
import com.br.dotazone.view.fragment.DialogError.TypeError
import com.br.dotazone.view.fragment.ItemFragment
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


class ItemAsync(private val mFragment: Fragment) : AsyncTask<Void?, Void?, String?>() {
	private var mProgressDialog: ProgressDialog
	private val mRequest: RequestREST
	private val mAdapterAction: AdapterAction
	private var mItemAttribs: MutableList<ItemAtrrib> = mutableListOf()
	private val mItems: List<Item> = ArrayList()

	override fun doInBackground(vararg p0: Void?): String? {
		var result: String? = null
		var resultAttrib: String? = null
		try {
			val assetManager = mFragment.activity!!.assets
			val inputStreamItemAttrib = assetManager.open("attribs_item.txt")
			resultAttrib = convertStreamToString(inputStreamItemAttrib)
			result = mRequest.getHttpRequest(getUrlItem(mFragment.activity!!))
			if (items.isEmpty()) {
				createItemAttrib(resultAttrib)
				createItem(result)
			}
		} catch (e: Exception) {
			Log.e(TAG, "Erro ao tentar consultar o arquivo de Item.--------")
			val fragmentError = newFragmentDialog(mFragment.activity!!.resources
					.getString(R.string.error_item_connection), (mFragment.activity as BaseActivity?)!!, TypeError.ONE_OPTIONS)
			// DotaZoneBrain.mItems = new ArrayList<Item>();
			fragmentError.show(mFragment.activity!!.supportFragmentManager, "Item")
		}
		return result
	}

	private fun createItemAttrib(result: String) {
		try {
			val jsonResponse = JSONObject(result)
			val mainNode = jsonResponse["itemdata"] as JSONObject
			val keys: Iterator<*> = mainNode.keys()
			mItemAttribs = ArrayList()
			while (keys.hasNext()) {
				val key = keys.next() as String
				if (mainNode[key] is JSONObject) {
					val itemAttrib = ItemAtrrib()
					val jsonChild = mainNode.getJSONObject(key)
					try {
						itemAttrib.id = key
						itemAttrib.damage = jsonChild.getInt(ItemAttribElementy.DAMAGE.toString().toLowerCase())
						itemAttrib.agility = jsonChild.getInt(ItemAttribElementy.AGILITY.toString().toLowerCase())
						itemAttrib.strength = jsonChild.getInt(ItemAttribElementy.STRENGTH.toString().toLowerCase())
						itemAttrib.inteligence = jsonChild.getInt(ItemAttribElementy.INTELIGENCE.toString().toLowerCase())
						itemAttrib.armor = jsonChild.getInt(ItemAttribElementy.ARMOR.toString().toLowerCase())
						itemAttrib.ms = jsonChild.getString(ItemAttribElementy.MS.toString().toLowerCase())
						mItemAttribs.add(itemAttrib)
					} catch (e: JSONException) {
						Log.e(TAG, "No item [" + itemAttrib.id + "  -]" + e)
					}
				}
			}
		} catch (e: JSONException) {
			val fragmentError = newFragmentDialog(mFragment.activity!!.resources.getString(R.string.error_json_item),
					(mFragment.activity as BaseActivity?)!!, TypeError.ONE_OPTIONS)
			// DotaZoneBrain.mItems = new ArrayList<Item>();
			fragmentError.show(mFragment.activity!!.supportFragmentManager, "Item")
		}
	}

	fun createItem(result: String?) {
		try {
			val jsonResponse = JSONObject(result)
			val mainNode = jsonResponse["itemdata"] as JSONObject
			val keys: Iterator<*> = mainNode.keys()
			while (keys.hasNext()) {
				val key = keys.next() as String
				if (mainNode[key] is JSONObject) {
					val item = Item()
					val jsonChild = mainNode.getJSONObject(key)
					var componentsList: JSONArray? = null
					try {
						if (jsonChild.getString(ItemElementy.DNAME.toString().toLowerCase()) == "Dagon") {
							println()
						}
						item.id = jsonChild.getInt(ItemElementy.ID.toString().toLowerCase())
						item.description = jsonChild.getString(ItemElementy.DESC.toString().toLowerCase())
						item.mc = jsonChild.getString(ItemElementy.MC.toString().toLowerCase())
						item.isCreated = jsonChild.getBoolean(ItemElementy.CREATED.toString().toLowerCase())
						item.imageName = jsonChild.getString(ItemElementy.IMG.toString().toLowerCase())
						item.qual = jsonChild.getString(ItemElementy.QUAL.toString().toLowerCase())
						item.name = jsonChild.getString(ItemElementy.DNAME.toString().toLowerCase())
						item.lore = jsonChild.getString(ItemElementy.LORE.toString().toLowerCase())
						item.isCreated = jsonChild.getBoolean(ItemElementy.CREATED.toString().toLowerCase())
						item.cost = jsonChild.getInt(ItemElementy.COST.toString().toLowerCase())
						item.notes = jsonChild.getString(ItemElementy.NOTES.toString().toLowerCase())
						item.attribute = jsonChild.getString(ItemElementy.ATTRIB.toString().toLowerCase())
						item.cloudown = jsonChild.getString(ItemElementy.CD.toString().toLowerCase())
						try {
							componentsList = jsonChild.getJSONArray(ItemElementy.COMPONENTS.toString().toLowerCase())
						} catch (e: Exception) {

							// Trata os itens que não tem um componente.
							Log.i(TAG, "no improvement items[" + item.name + "  -]")
						}
						if (componentsList != null) {
							for (i in 0 until componentsList.length()) {
								item.components.add(componentsList.getString(i))
								Log.i(TAG, "Improvement items[" + item.name + "  -]")
							}
						}
						identifyItemCategoryAndAdd(item)
					} catch (e: JSONException) {
						Log.e(TAG, "No item [" + item.name + "  -]" + e)
					}
				}
			}
		} catch (e: JSONException) {
			val fragmentError = newFragmentDialog(mFragment.activity!!.resources.getString(R.string.error_json_item),
					(mFragment.activity as BaseActivity?)!!, TypeError.ONE_OPTIONS)
			// DotaZoneBrain.mItems = new ArrayList<Item>();
			fragmentError.show(mFragment.activity!!.supportFragmentManager, "Item")
		}
	}

	private fun identifyItemCategoryAndAdd(item: Item) {
		val pos = item.imageName.lastIndexOf('.')
		val id = mFragment.activity!!.resources
				.getIdentifier(item.imageName.substring(0, pos), "drawable", mFragment.activity!!.packageName)
		for (itemAttrib in mItemAttribs!!) {
			if (item.imageName == itemAttrib.id + ".png") {
				item.setmItemAttrib(itemAttrib)
				Log.i(TAG, "attribute item add-" + itemAttrib.id)
				break
			}
		}

		// Verifica se existem a imagem cadastrada no json.
		if (id > 0) {
			items.add(item)
			//mItems.add(item);
			Log.i(TAG, "adding Item [" + item.imageName + "]")
		}
	}

	override fun onPostExecute(result: String?) {
		super.onPostExecute(result)
		mProgressDialog.dismiss()
		mAdapterAction.initList(null)
	}

	override fun onPreExecute() {
		mProgressDialog = ProgressDialog(mFragment.activity)
		mProgressDialog.setMessage("Carregando...")
		mProgressDialog.show()
		mProgressDialog.setCancelable(false)
	}

	init {
		mRequest = RequestREST()
		mProgressDialog = ProgressDialog(mFragment.activity)
		mAdapterAction = mFragment as ItemFragment
	}
}

