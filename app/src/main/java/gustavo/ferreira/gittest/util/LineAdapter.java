package gustavo.ferreira.gittest.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import gustavo.ferreira.gittest.R;
import gustavo.ferreira.gittest.data.Repos;

/**
 * Created by Gustavo on 02/06/2018.
 */

public class LineAdapter extends RecyclerView.Adapter<LineHolder>{
    private final List<Repos> repos;

    public LineAdapter(List texts){
        repos = texts;
    }

    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_line_view, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, int position){
        holder.title.setText(repos.get(position).getName());
        holder.language.setText(repos.get(position).getLanguage());
    }

    @Override
    public int getItemCount(){
        return repos != null ? repos.size() : 0;
    }
}
