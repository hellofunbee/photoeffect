package hello.world.paints;

import hello.world.builder.piceffect.R;
import hello.world.domain.FilterInfo;
import hello.world.domain.Mapp;

import java.util.ArrayList;
import java.util.List;

import HaoRan.ImageFilter.AutoAdjustFilter;
import HaoRan.ImageFilter.BannerFilter;
import HaoRan.ImageFilter.BigBrotherFilter;
import HaoRan.ImageFilter.BlackWhiteFilter;
import HaoRan.ImageFilter.BlindFilter;
import HaoRan.ImageFilter.BlockPrintFilter;
import HaoRan.ImageFilter.BrickFilter;
import HaoRan.ImageFilter.BrightContrastFilter;
import HaoRan.ImageFilter.CleanGlassFilter;
import HaoRan.ImageFilter.ColorQuantizeFilter;
import HaoRan.ImageFilter.ColorToneFilter;
import HaoRan.ImageFilter.ComicFilter;
import HaoRan.ImageFilter.EdgeFilter;
import HaoRan.ImageFilter.FeatherFilter;
import HaoRan.ImageFilter.FillPatternFilter;
import HaoRan.ImageFilter.FilmFilter;
import HaoRan.ImageFilter.FocusFilter;
import HaoRan.ImageFilter.GammaFilter;
import HaoRan.ImageFilter.GaussianBlurFilter;
import HaoRan.ImageFilter.Gradient;
import HaoRan.ImageFilter.HslModifyFilter;
import HaoRan.ImageFilter.IllusionFilter;
import HaoRan.ImageFilter.InvertFilter;
import HaoRan.ImageFilter.LensFlareFilter;
import HaoRan.ImageFilter.LightFilter;
import HaoRan.ImageFilter.LomoFilter;
import HaoRan.ImageFilter.MirrorFilter;
import HaoRan.ImageFilter.MistFilter;
import HaoRan.ImageFilter.MonitorFilter;
import HaoRan.ImageFilter.MosaicFilter;
import HaoRan.ImageFilter.NeonFilter;
import HaoRan.ImageFilter.NightVisionFilter;
import HaoRan.ImageFilter.NoiseFilter;
import HaoRan.ImageFilter.OilPaintFilter;
import HaoRan.ImageFilter.OldPhotoFilter;
import HaoRan.ImageFilter.PaintBorderFilter;
import HaoRan.ImageFilter.PixelateFilter;
import HaoRan.ImageFilter.PosterizeFilter;
import HaoRan.ImageFilter.RadialDistortionFilter;
import HaoRan.ImageFilter.RainBowFilter;
import HaoRan.ImageFilter.RaiseFrameFilter;
import HaoRan.ImageFilter.RectMatrixFilter;
import HaoRan.ImageFilter.ReflectionFilter;
import HaoRan.ImageFilter.ReliefFilter;
import HaoRan.ImageFilter.SaturationModifyFilter;
import HaoRan.ImageFilter.SceneFilter;
import HaoRan.ImageFilter.SepiaFilter;
import HaoRan.ImageFilter.SharpFilter;
import HaoRan.ImageFilter.ShiftFilter;
import HaoRan.ImageFilter.SmashColorFilter;
import HaoRan.ImageFilter.SoftGlowFilter;
import HaoRan.ImageFilter.SupernovaFilter;
import HaoRan.ImageFilter.ThreeDGridFilter;
import HaoRan.ImageFilter.ThresholdFilter;
import HaoRan.ImageFilter.TileReflectionFilter;
import HaoRan.ImageFilter.TintFilter;
import HaoRan.ImageFilter.VideoFilter;
import HaoRan.ImageFilter.VignetteFilter;
import HaoRan.ImageFilter.VintageFilter;
import HaoRan.ImageFilter.WaterWaveFilter;
import HaoRan.ImageFilter.XRadiationFilter;
import HaoRan.ImageFilter.YCBCrLinearFilter;
import HaoRan.ImageFilter.ZoomBlurFilter;
import HaoRan.ImageFilter.Distort.BulgeFilter;
import HaoRan.ImageFilter.Distort.RippleFilter;
import HaoRan.ImageFilter.Distort.TwistFilter;
import HaoRan.ImageFilter.Distort.WaveFilter;
import HaoRan.ImageFilter.Textures.CloudsTexture;
import HaoRan.ImageFilter.Textures.LabyrinthTexture;
import HaoRan.ImageFilter.Textures.MarbleTexture;
import HaoRan.ImageFilter.Textures.TextileTexture;
import HaoRan.ImageFilter.Textures.TexturerFilter;
import HaoRan.ImageFilter.Textures.WoodTexture;
import android.graphics.Color;
import android.view.LayoutInflater;
/**
 * @since 2015 06 24
 * @author funbee {@link https://github.com/hellofunbee/gifEffect}
 */
public class Pens {

	public static int[] getPen_mask() {
		int[] paints = new int[] {};
		return paints;
	}

	public static int[] getPen_edge() {

		int[] paints = new int[] { R.drawable.frame_around1,
				R.drawable.frame_around2 };
		return paints;

	}

	public static int[] getPen_stickers() {

		int[] paints = new int[] { R.drawable.camera, R.drawable.photo,
				R.drawable.camera, R.drawable.photo };
		return paints;

	}

	public static int[] getPen_textStickers() {

		int[] paints = new int[] { R.drawable.camera, R.drawable.photo,
				R.drawable.camera, R.drawable.photo };
		return paints;

	}

	public static int[] getPen_filter() {

		int[] paints = new int[] {
				R.drawable.video_filter1,
				R.drawable.video_filter2,
				R.drawable.video_filter3,
				R.drawable.video_filter4,
				R.drawable.tilereflection_filter1,
				R.drawable.tilereflection_filter2,
				R.drawable.fillpattern_filter,
				R.drawable.fillpattern_filter1,
				R.drawable.mirror_filter1,
				R.drawable.ycb_crlinear_filter,
				R.drawable.ycb_crlinear_filter2,
				R.drawable.texturer_filter,
				R.drawable.texturer_filter1,
				R.drawable.texturer_filter2,
				R.drawable.texturer_filter3,
				R.drawable.texturer_filter4,
				R.drawable.hslmodify_filter,
				R.drawable.hslmodify_filter0,
				R.drawable.hslmodify_filter1,
				R.drawable.hslmodify_filter2,
				R.drawable.hslmodify_filter3,
				R.drawable.hslmodify_filter4,
				R.drawable.hslmodify_filter5,
				R.drawable.hslmodify_filter6,
				R.drawable.hslmodify_filter7,

				// v0.3
				R.drawable.zoomblur_filter, R.drawable.threedgrid_filter,
				R.drawable.colortone_filter, R.drawable.colortone_filter2,
				R.drawable.colortone_filter3, R.drawable.colortone_filter4,
				R.drawable.softglow_filter, R.drawable.tilereflection_filter,
				R.drawable.blind_filter1, R.drawable.blind_filter2,
				R.drawable.raiseframe_filter, R.drawable.shift_filter,
				R.drawable.wave_filter, R.drawable.bulge_filter,
				R.drawable.twist_filter, R.drawable.ripple_filter,
				R.drawable.illusion_filter, R.drawable.supernova_filter,
				R.drawable.lensflare_filter, R.drawable.posterize_filter,
				R.drawable.gamma_filter, R.drawable.sharp_filter,

				R.drawable.invert_filter, R.drawable.invert_filter,
				R.drawable.invert_filter, R.drawable.invert_filter,
				R.drawable.invert_filter, R.drawable.invert_filter,
				R.drawable.invert_filter, R.drawable.invert_filter,
				R.drawable.invert_filter, R.drawable.invert_filter,
				R.drawable.invert_filter, R.drawable.invert_filter,

				R.drawable.invert_filter, R.drawable.blackwhite_filter,
				R.drawable.edge_filter, R.drawable.pixelate_filter,
				R.drawable.neon_filter, R.drawable.bigbrother_filter,
				R.drawable.monitor_filter, R.drawable.relief_filter,
				R.drawable.brightcontrast_filter,
				R.drawable.saturationmodity_filter,
				R.drawable.threshold_filter, R.drawable.noisefilter,
				R.drawable.banner_filter1, R.drawable.banner_filter2,
				R.drawable.rectmatrix_filter, R.drawable.blockprint_filter,
				R.drawable.brick_filter, R.drawable.gaussianblur_filter,
				R.drawable.light_filter, R.drawable.mosaic_filter,
				R.drawable.mosaic_filter, R.drawable.oilpaint_filter,
				R.drawable.radialdistortion_filter,
				R.drawable.reflection1_filter, R.drawable.reflection2_filter,
				R.drawable.saturationmodify_filter,
				R.drawable.smashcolor_filter, R.drawable.tint_filter,
				R.drawable.vignette_filter, R.drawable.autoadjust_filter,
				R.drawable.colorquantize_filter, R.drawable.waterwave_filter,
				R.drawable.vintage_filter, R.drawable.oldphoto_filter,
				R.drawable.sepia_filter, R.drawable.rainbow_filter,
				R.drawable.feather_filter, R.drawable.xradiation_filter,
				R.drawable.nightvision_filter

		};
		return paints;
	}

	public static int[] getEdgeFrames(int id) {
		int[] paints = null;
		switch (id) {
		case R.drawable.frame_around1:
			paints = new int[] { R.drawable.frame_around1_left_top,
					R.drawable.frame_around1_right_top,
					R.drawable.frame_around1_left_bottom,
					R.drawable.frame_around1_right_bottom,
					R.drawable.frame_around1_top,
					R.drawable.frame_around1_bottom,
					R.drawable.frame_around1_left,
					R.drawable.frame_around1_right };
			break;
		case R.drawable.frame_around2:
			paints = new int[] { R.drawable.frame_around2_left_top,
					R.drawable.frame_around2_right_top,
					R.drawable.frame_around2_left_bottom,
					R.drawable.frame_around2_right_bottom,
					R.drawable.frame_around2_top,
					R.drawable.frame_around2_bottom,
					R.drawable.frame_around2_left,
					R.drawable.frame_around2_right };

			break;
		}

		return paints;

	}

}
