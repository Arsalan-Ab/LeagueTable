package com.example.leaguetable.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguetable.databinding.TableHeaderBinding
import com.example.leaguetable.databinding.TableListBinding
import com.example.leaguetable.model.Team

class TableAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class HeaderViewHolder(val headerBinding: TableHeaderBinding) :
        RecyclerView.ViewHolder(headerBinding.root)

    inner class TableViewHolder(val listBinding: TableListBinding) :
        RecyclerView.ViewHolder(listBinding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.strTeam == newItem.strTeam
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            return HeaderViewHolder(
                TableHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else
            return TableViewHolder(
                TableListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 0) {
            return
        } else {
            val tableHolder = holder as TableViewHolder
            val team = differ.currentList[position - 1]
            tableHolder.listBinding.apply {
                txtRank.text = team.intRank
                txtTeam.text = team.strTeam
                txtMp.text = team.intPlayed
                txtWin.text = team.intWin
                txtDraw.text = team.intDraw
                txtLoss.text = team.intLoss
                txtPoints.text = team.intPoints
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        } else
            return 1
    }

}