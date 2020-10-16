package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.DetailRecipeActivity;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Response.ResponseRecipe;

public class AdapaterRecipe extends RecyclerView.Adapter<AdapaterRecipe.ViewHolder> {
    private Context context;
    private List<ResponseRecipe> list_recipe;
    public AdapaterRecipe(Context context, List<ResponseRecipe> list_recipe){
        this.context = context;
        this.list_recipe = list_recipe;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String URL_IMAGE = "http://gkekasongan.000webhostapp.com/RecipeApi/Images/" + list_recipe.get(position).getImage();
        Picasso.with(context).load(URL_IMAGE).into(holder.image);
        holder.tv_recipe_name.setText(list_recipe.get(position).getRecipeName() + " Recipe");
        holder.tv_created_by.setText("Created by " + list_recipe.get(position).getCreateBy());
        holder.linearLayout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setIntent = new Intent(context, DetailRecipeActivity.class);
                setIntent.putExtra("RECIPE_NAME", list_recipe.get(position).getRecipeName());
                setIntent.putExtra("CREATED", list_recipe.get(position).getCreateBy());
                setIntent.putExtra("IMAGE", URL_IMAGE);
                setIntent.putExtra("INGREDIENTS", list_recipe.get(position).getIngredients());
                setIntent.putExtra("STEPS", list_recipe.get(position).getSteps());
                context.startActivity(setIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_recipe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout_item;
        ImageView image;
        TextView tv_recipe_name;
        TextView tv_created_by;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout_item = (LinearLayout) itemView.findViewById(R.id.layout_item);
            image = (ImageView) itemView.findViewById(R.id.img_recipe);
            tv_recipe_name = (TextView) itemView.findViewById(R.id.tv_recipe_name);
            tv_created_by = (TextView) itemView.findViewById(R.id.tv_create_by);
        }
    }
}
