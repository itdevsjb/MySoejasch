// Generated by view binder compiler. Do not edit!
package com.tpsmedia.appmanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tpsmedia.appmanager.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogExitBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatButton btnNo;

  @NonNull
  public final AppCompatButton btnYes;

  @NonNull
  public final TextView tvContent;

  @NonNull
  public final TextView tvTitle;

  private DialogExitBinding(@NonNull LinearLayout rootView, @NonNull AppCompatButton btnNo,
      @NonNull AppCompatButton btnYes, @NonNull TextView tvContent, @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.btnNo = btnNo;
    this.btnYes = btnYes;
    this.tvContent = tvContent;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogExitBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogExitBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_exit, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogExitBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_no;
      AppCompatButton btnNo = ViewBindings.findChildViewById(rootView, id);
      if (btnNo == null) {
        break missingId;
      }

      id = R.id.btn_yes;
      AppCompatButton btnYes = ViewBindings.findChildViewById(rootView, id);
      if (btnYes == null) {
        break missingId;
      }

      id = R.id.tv_content;
      TextView tvContent = ViewBindings.findChildViewById(rootView, id);
      if (tvContent == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new DialogExitBinding((LinearLayout) rootView, btnNo, btnYes, tvContent, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
