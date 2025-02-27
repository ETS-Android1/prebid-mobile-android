package org.prebid.mobile.renderingtestapp.plugplay.bidding.max

import android.view.ViewGroup
import android.widget.FrameLayout
import com.applovin.mediation.MaxAdFormat
import com.applovin.mediation.adapters.prebid.utils.MaxMediationBannerUtils
import com.applovin.mediation.ads.MaxAdView
import com.applovin.sdk.AppLovinSdkUtils
import kotlinx.android.synthetic.main.fragment_bidding_banner_applovin_max.*
import org.prebid.mobile.AdSize
import org.prebid.mobile.api.mediation.MediationBannerAdUnit

class MaxBannerFragmentAdaptive : MaxBannerFragment() {

    override fun initAd(): Any? {
        adView = MaxAdView(adUnitId, requireContext())
        adView?.setListener(createListener())

        val widthPx = ViewGroup.LayoutParams.MATCH_PARENT
        val heightDp = MaxAdFormat.BANNER.getAdaptiveSize(requireActivity()).height
        val heightPx = AppLovinSdkUtils.dpToPx(requireContext(), heightDp)
        adView?.layoutParams = FrameLayout.LayoutParams(widthPx, heightPx)
        adView?.setExtraParameter("adaptive_banner", "true")
        viewContainer.addView(adView)

        val mediationUtils = MaxMediationBannerUtils(adView)
        adUnit = MediationBannerAdUnit(
            requireContext(),
            configId,
            AdSize(width, height),
            mediationUtils
        )
        adUnit?.addAdditionalSizes(AdSize(728, 90))
        adUnit?.setRefreshInterval(refreshDelay)
        return adUnit
    }

}