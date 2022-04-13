package com.example.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> mNewsList;
        class ViewHolder extends RecyclerView.ViewHolder{

            TextView newsTitleText;
            public ViewHolder(View view){
                super(view);
                newsTitleText=view.findViewById(R.id.news_title);
            }
        }
        public  NewsAdapter(List<News> mNewsList){
            this.mNewsList=mNewsList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item,viewGroup,false);
            final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    News news=mNewsList.get(holder.getAdapterPosition());
                    if(isTwoPane){
                        NewsContentFragment newsContentFragment=(NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    }else{
                        NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            News news=mNewsList.get(i);
            viewHolder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_title_frag,container,false);
        RecyclerView newsTitleRecyclerView=(RecyclerView)view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter adapter =new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    private List<News> getNews(){
        List<News> newsList=new ArrayList<>();
        for(int i=1;i<50;i++){
            News news=new News("This is news title"+i,getRandomLengthContent("This is news conten")+i+".");
            newsList.add(news);
        }
        return newsList;
    }
    private String getRandomLengthContent(String content){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<length;i++){
            stringBuilder.append(content);
        }
        return stringBuilder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
        }else{
            isTwoPane=false;
        }
    }
}
