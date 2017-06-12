package com.janatee.app.euler11;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.janatee.app.euler11.databinding.ItemProblemBinding;

/**
 * Created by jaesunlee on 2017. 6. 1..
 */

public class ProblemAdapter extends RecyclerView.Adapter
{
    private int[][] problem;

    ProblemAdapter(int[][] problem)
    {
        this.problem = problem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_problem, parent, false);
        return new ProblemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        ProblemViewHolder viewHolder = (ProblemViewHolder)holder;
        int i = position / 20;
        int j = position % 20;
        int item = problem[i][j];
        viewHolder.binding.setItem(item);
    }

    @Override
    public int getItemCount()
    {
        return problem.length * problem.length;
    }

    public int getItem(int i, int j)
    {
        return problem[i][j];
    }

    public int[][] getProblem()
    {
        return problem;
    }

    public void updateProblem(int[][] problem)
    {
        this.problem = problem;
        notifyDataSetChanged();
    }

    class ProblemViewHolder extends RecyclerView.ViewHolder
    {
        private ItemProblemBinding binding;

        ProblemViewHolder(View itemView)
        {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
