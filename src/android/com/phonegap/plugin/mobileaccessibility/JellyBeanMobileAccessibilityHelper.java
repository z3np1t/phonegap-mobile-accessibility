package com.phonegap.plugin.mobileaccessibility;

import com.phonegap.plugin.mobileaccessibility.MobileAccessibility;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class JellyBeanMobileAccessibilityHelper extends
		IceCreamSandwichMobileAccessibilityHelper {
	
	@Override
	public void initialize(MobileAccessibility mobileAccessibility) {
		super.initialize(mobileAccessibility);
		mParent = mobileAccessibility.webView.getParentForAccessibility();
	}
	
	@Override
	public void announceForAccessibility(CharSequence text) {
        if (mAccessibilityManager.isEnabled() && mParent != null) {
            AccessibilityEvent event = AccessibilityEvent.obtain(
                    AccessibilityEvent.TYPE_ANNOUNCEMENT);
            mWebView.onInitializeAccessibilityEvent(event);
            event.getText().add(text);
            event.setContentDescription(null);
            mParent.requestSendAccessibilityEvent(mWebView, event);
        }
    }
}
