package indestructibles.pe.ofertongo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import indestructibles.pe.ofertongo.Activity.ProductDetailsActivity;
import indestructibles.pe.ofertongo.Entities.CardPromotionsBussines;
import indestructibles.pe.ofertongo.Entities.Products;
import indestructibles.pe.ofertongo.R;

public class RecyclerViewAdapterProducts extends RecyclerView.Adapter<RecyclerViewAdapterProducts.ViewHolder> {
    private Context mContext;
    private List<Products> products;
    public RecyclerViewAdapterProducts(Context mContext, List<Products> products) {
        this.mContext = mContext;
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterProducts.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.item_product_carf,parent ,false);
        return new RecyclerViewAdapterProducts.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterProducts.ViewHolder holder, int position) {
        holder.label_name.setText(products.get(position).getName());
        holder.label_price.setText(products.get(position).getPriceString());
        holder.imagen_card.setImageUrl(products.get(position).getImage_url());
        holder.imagen_card.setDefaultImageResId( R.mipmap.icon_store_48);
        holder.imagen_card.setErrorImageResId( R.mipmap.icon_store_48);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView label_name;
        TextView label_price;
        ANImageView imagen_card;

        private CardView promotion;
        public ViewHolder(View itemView) {
            super(itemView);
            label_name=(TextView)itemView.findViewById(R.id.nombre);
            label_price=(TextView)itemView.findViewById(R.id.precio);
            imagen_card=(ANImageView) itemView.findViewById(R.id.card_image);
            promotion= itemView.findViewById(R.id.constraint_content);
            promotion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext ,"Se hizo click "/*+id*/,Toast.LENGTH_SHORT).show();
                    Context context = view.getContext();
                    context.startActivity(
                            new Intent(context, ProductDetailsActivity.class).putExtra("idStore","1")
                            /*.putExtras(article.toBundle())*/);

                    //dialogPromotion.dismiss();
                }
            });
        }
    }
}
