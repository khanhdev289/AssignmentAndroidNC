package khanhnqph30151.fptpoly.assignment.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import khanhnqph30151.fptpoly.assignment.LoginActivity;
import khanhnqph30151.fptpoly.assignment.R;
import khanhnqph30151.fptpoly.assignment.adapter.AdapterFavorite;
import khanhnqph30151.fptpoly.assignment.data.FavoriteDAO;
import khanhnqph30151.fptpoly.assignment.data.UserDAO;
import khanhnqph30151.fptpoly.assignment.model.Favorite;
import khanhnqph30151.fptpoly.assignment.model.User;


public class UserFragment extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    String currentPhotoPath;
    UserDAO thanhVienDAO;
    TextView choose;
    ArrayList<User> list;
    User user;
    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvLogout = view.findViewById(R.id.tv_user_dangxuat);
        choose = view.findViewById(R.id.tv_user_thayavt);
        imageView = view.findViewById(R.id.iv_user_avt);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "chup anh deo len", Toast.LENGTH_SHORT).show();
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getContext(),
                                "khanhnqph30151.fptpoly.assignment.fragment.fileprovider",
                                photoFile);

                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


                    }
                }
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            File photoFile = new File(currentPhotoPath);
            if (photoFile.exists()) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "khanhnqph30151.fptpoly.assignment.fragment.fileprovider",
                        photoFile);
                thanhVienDAO = new UserDAO(getContext());
                user = new User();
                String id = user.getUser();
                String avatarPath = photoFile.getAbsolutePath();
                boolean isUpdated = thanhVienDAO.updateAvatar(user);
                if (isUpdated) {
                    user.setAvatar(avatarPath);
                    Toast.makeText(getContext(), "Cập Nhật Ảnh Thành Công", Toast.LENGTH_SHORT).show();
                    setPic();
                } else {
                    Toast.makeText(getContext(), "Cập Nhật Ảnh Thất Bại", Toast.LENGTH_SHORT).show();
                }
//                if (thanhVienDAO.updateAvatar(Integer.parseInt(id), avatarPath)){
//                    Toast.makeText(getContext(), "Cập Nhật Ảnh Thành Công", Toast.LENGTH_SHORT).show();
//                    setPic();
//                } else {
//                    Toast.makeText(getContext(), "Cập Nhật Ảnh Thất Bại", Toast.LENGTH_SHORT).show();
//                }
            }
        }


    }


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void setPic() {

        int targetW = imageView.getMaxWidth();
        int targetH = imageView.getMaxHeight();


        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;


        int scaleFactor = Math.max(1, Math.min(photoW / targetW, photoH / targetH));

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
        imageView.setRotation(0);

    }
}