package Activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.karenovich.goenglish.R;
import com.karenovich.goenglish.databinding.FragmentVkBinding;

import Data.ConfigUser;

public class VkFragmentUp extends Fragment {

    FragmentVkBinding binding;
    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        url = "https://oauth.vk.com/authorize?client_id=51497747&display=mobile&redirect_uri=https://oauth.vk.com/blank.html&scope=email&response_type=token&v=5.131&state=123456";
        CookieManager.getInstance().removeAllCookies(null);
        binding.web.clearCache(true);
        binding.web.loadUrl(url);
        binding.web.setWebViewClient(getWebViewClient());
    }

    public WebViewClient getWebViewClient(){
        return new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String urlString = request.getUrl().toString();
                if(urlString.contains("https://oauth.vk.com/blank.html")){
                    ConfigUser.getInstance().access_token = Uri.parse(urlString.replace("#", "?")).getQueryParameter("access_token");
                    ConfigUser.getInstance().email = Uri.parse(urlString.replace("#", "?")).getQueryParameter("email");
                    ConfigUser.getInstance().user_id = Uri.parse(urlString.replace("#", "?")).getQueryParameter("user_id");
                    replaceFragment(new VkSignUpFragment());
                    return false;
                }
                view.loadUrl(urlString);
                return true;
            }
        };
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.authFrame, fragment);
        ft.commit();
    }
}