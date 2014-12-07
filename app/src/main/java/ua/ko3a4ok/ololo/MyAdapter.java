package ua.ko3a4ok.ololo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ko3a4ok on 12/4/14.
 */
public class MyAdapter extends ArrayAdapter<String>  {
    private static final int TASK_TYPE = 0;
    private static final int IMAGE_TYPE = 1;

    private MyApplication application;
    private Context ctx;

    public MyAdapter(Context ctx, MyApplication application) {
        super(ctx, R.layout.list_item, R.id.name, application.getTasks());
        this.ctx = ctx;
        this.application = application;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (type == IMAGE_TYPE) return getImageView(convertView, position);
        return getLoadingView(super.getView(position, convertView, parent), position);
    }

    private View getLoadingView(View convertView, final int position) {
        final String link = getItem(position);
        ImageHolder ih = application.getImageHolder(link);
        if (convertView.getTag() == null) {
            convertView.setTag(new ViewHolder(convertView));
        }
        if (ih == null) return convertView;
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.progress.setProgress(ih.getPercentage());
        if (ih.isFailed()) {
            holder.failed.setVisibility(View.VISIBLE);
            holder.failed.setText(ih.getRawData() == null ? R.string.network_problem : R.string.storing_problem);
            holder.failedLayout.setVisibility(View.VISIBLE);
            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    application.removeTask(position);
                    notifyDataSetChanged();
                }
            });
            holder.tryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (application.isServiceRunning())
                        Utils.startDownloadImage(((MyActivity) ctx).binder, link);
                    else
                        Toast.makeText(ctx, R.string.service_not_started, Toast.LENGTH_LONG).show();
                }
            });
        } else {
            holder.failed.setVisibility(View.INVISIBLE);
            holder.failedLayout.setVisibility(View.GONE);
        }
        return convertView;
    }

    private View getImageView(View convertView, int position) {
        if (convertView == null) {
            convertView = ((Activity)ctx).getLayoutInflater().inflate(R.layout.image_item, null);
            convertView.setTag(new ImageViewHolder(convertView));
        }
        final String link = getItem(position);
        ImageHolder ih = application.getImageHolder(link);

        ImageViewHolder ivh = (ImageViewHolder) convertView.getTag();
        ivh.name.setText(link);
        ivh.image.setImageURI(Uri.parse(ih.getUri()));
        return convertView;

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        final String link = getItem(position);
        ImageHolder ih = application.getImageHolder(link);
        return ih != null && ih.getUri() != null ? IMAGE_TYPE : TASK_TYPE;
    }

    private class ViewHolder {
        TextView name;
        ProgressBar progress;
        TextView failed;
        View failedLayout;
        View tryAgain;
        View remove;

        public ViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.name);
            progress = (ProgressBar) convertView.findViewById(R.id.progress);
            failed = (TextView) convertView.findViewById(R.id.failed);
            failedLayout = convertView.findViewById(R.id.failed_buttons);
            tryAgain =  failedLayout.findViewById(R.id.try_again);
            remove = failedLayout.findViewById(R.id.remove);
        }
    }

    private class ImageViewHolder {
        TextView name;
        ImageView image;

        public ImageViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.link);
            image = (ImageView) convertView.findViewById(R.id.img);
        }
    }



}
