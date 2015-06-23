package hello.world.paints;

import hello.world.builder.piceffect.R;
import hello.world.domain.Mapp;
import HaoRan.ImageFilter.AutoAdjustFilter;
import HaoRan.ImageFilter.BannerFilter;
import HaoRan.ImageFilter.BigBrotherFilter;
import HaoRan.ImageFilter.BlackWhiteFilter;
import HaoRan.ImageFilter.BlindFilter;
import HaoRan.ImageFilter.BlockPrintFilter;
import HaoRan.ImageFilter.BrickFilter;
import HaoRan.ImageFilter.BrightContrastFilter;
import HaoRan.ImageFilter.ColorQuantizeFilter;
import HaoRan.ImageFilter.ColorToneFilter;
import HaoRan.ImageFilter.EdgeFilter;
import HaoRan.ImageFilter.FeatherFilter;
import HaoRan.ImageFilter.FillPatternFilter;
import HaoRan.ImageFilter.GammaFilter;
import HaoRan.ImageFilter.GaussianBlurFilter;
import HaoRan.ImageFilter.HslModifyFilter;
import HaoRan.ImageFilter.IImageFilter;
import HaoRan.ImageFilter.IllusionFilter;
import HaoRan.ImageFilter.InvertFilter;
import HaoRan.ImageFilter.LensFlareFilter;
import HaoRan.ImageFilter.LightFilter;
import HaoRan.ImageFilter.MirrorFilter;
import HaoRan.ImageFilter.MistFilter;
import HaoRan.ImageFilter.MonitorFilter;
import HaoRan.ImageFilter.NeonFilter;
import HaoRan.ImageFilter.NightVisionFilter;
import HaoRan.ImageFilter.NoiseFilter;
import HaoRan.ImageFilter.OilPaintFilter;
import HaoRan.ImageFilter.OldPhotoFilter;
import HaoRan.ImageFilter.PixelateFilter;
import HaoRan.ImageFilter.PosterizeFilter;
import HaoRan.ImageFilter.RadialDistortionFilter;
import HaoRan.ImageFilter.RainBowFilter;
import HaoRan.ImageFilter.RaiseFrameFilter;
import HaoRan.ImageFilter.RectMatrixFilter;
import HaoRan.ImageFilter.ReflectionFilter;
import HaoRan.ImageFilter.ReliefFilter;
import HaoRan.ImageFilter.SaturationModifyFilter;
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

public class Filters {
public static IImageFilter getFilter(int l,Mapp app){
	IImageFilter filter = null;
	switch (l) {
	case R.drawable.video_filter1:
		filter = new VideoFilter(VideoFilter.VIDEO_TYPE.VIDEO_STAGGERED);
		break;

	case R.drawable.video_filter2:
			filter = new  VideoFilter(VideoFilter.VIDEO_TYPE.VIDEO_TRIPED);break;
	case R.drawable.video_filter3:
			filter = new  VideoFilter(VideoFilter.VIDEO_TYPE.VIDEO_3X3);break;
	case R.drawable.video_filter4:
			filter = new  VideoFilter(VideoFilter.VIDEO_TYPE.VIDEO_DOTS);break;
	case R.drawable.tilereflection_filter1:
			filter = new  TileReflectionFilter(20, 8, 45, (byte) 1);break;
	case R.drawable.tilereflection_filter2:
			filter = new  TileReflectionFilter(20, 8, 45, (byte) 2);break;
	case R.drawable.fillpattern_filter:
			filter = new  FillPatternFilter(app, R.drawable.texture1);break;
	case R.drawable.fillpattern_filter1:
			filter = new  FillPatternFilter(app, R.drawable.texture2);break;
	case R.drawable.mirror_filter1:
			filter = new  MirrorFilter(true);break;
	case R.drawable.mirror_filter2:
			filter = new  MirrorFilter(false);break;
	case R.drawable.ycb_crlinear_filter:
					filter = new  YCBCrLinearFilter(new YCBCrLinearFilter.Range(
							-0.3f, 0.3f));break;
	case R.drawable.ycb_crlinear_filter2:
			filter = new  YCBCrLinearFilter(new  YCBCrLinearFilter.Range(-0.276f,
					0.163f), new YCBCrLinearFilter.Range(-0.202f, 0.5f));break;
	case R.drawable.texturer_filter:
			filter = new  TexturerFilter(new CloudsTexture(), 0.8f, 0.8f);break;
	case R.drawable.texturer_filter1:
			filter = new  TexturerFilter(new LabyrinthTexture(), 0.8f, 0.8f);break;
	case R.drawable.texturer_filter2:
			filter = new  TexturerFilter(new MarbleTexture(), 1.8f, 0.8f);break;
	case R.drawable.texturer_filter3:
			filter = new  TexturerFilter(new WoodTexture(), 0.8f, 0.8f);break;
	case R.drawable.texturer_filter4:
			filter = new  TexturerFilter(new TextileTexture(), 0.8f, 0.8f);break;
	case R.drawable.hslmodify_filter:
			filter = new  HslModifyFilter(20f);break;
	case R.drawable.hslmodify_filter0:
			filter = new  HslModifyFilter(40f);break;
	case R.drawable.hslmodify_filter1:
			filter = new  HslModifyFilter(60f);break;
	case R.drawable.hslmodify_filter2:
			filter = new  HslModifyFilter(80f);break;
	case R.drawable.hslmodify_filter3:
			filter = new  HslModifyFilter(100f);break;
	case R.drawable.hslmodify_filter4:
			filter = new  HslModifyFilter(150f);break;
	case R.drawable.hslmodify_filter5:
			filter = new  HslModifyFilter(200f);break;
	case R.drawable.hslmodify_filter6:
			filter = new  HslModifyFilter(250f);break;
	case R.drawable.hslmodify_filter7:
			filter = new  HslModifyFilter(300f);break;

	// v0.3
	case R.drawable.zoomblur_filter:
			filter = new  ZoomBlurFilter(30);break;
	case R.drawable.threedgrid_filter:
			filter = new  ThreeDGridFilter(16, 100);break;
	case R.drawable.colortone_filter:
			filter = new  ColorToneFilter(Color.rgb(33, 168, 254), 192);break;
	case R.drawable.colortone_filter2:
			filter = new  ColorToneFilter(0x00FF00, 192);break;// green
	case R.drawable.colortone_filter3:
			filter = new  ColorToneFilter(0xFF0000, 192);break;// blue
	case R.drawable.colortone_filter4:
			filter = new  ColorToneFilter(0x00FFFF, 192);break;// yellow
	case R.drawable.softglow_filter:
			filter = new  SoftGlowFilter(10, 0.1f, 0.1f);break;
	case R.drawable.tilereflection_filter:
			filter = new  TileReflectionFilter(20, 8);break;
	case R.drawable.blind_filter1:
			filter = new  BlindFilter(true, 96, 100, 0xffffff);break;
	case R.drawable.blind_filter2:
			filter = new  BlindFilter(false, 96, 100, 0x000000);break;
	case R.drawable.raiseframe_filter:
			filter = new  RaiseFrameFilter(20);break;
	case R.drawable.shift_filter:
			filter = new  ShiftFilter(10);break;
	case R.drawable.wave_filter: filter = new  WaveFilter(
			25, 10);break;
	case R.drawable.bulge_filter:
			filter = new  BulgeFilter(-97);break;
	case R.drawable.twist_filter:
			filter = new  TwistFilter(27, 106);break;
	case R.drawable.ripple_filter:
			filter = new  RippleFilter(38, 15, true);break;
	case R.drawable.illusion_filter:
			filter = new  IllusionFilter(3);break;
	case R.drawable.supernova_filter:
			filter = new  SupernovaFilter(0x00FFFF, 20, 100);break;
	case R.drawable.lensflare_filter:
			filter = new  LensFlareFilter();break;
	case R.drawable.posterize_filter:
			filter = new  PosterizeFilter(2);break;
	case R.drawable.gamma_filter:
			filter = new  GammaFilter(50);break;
	case R.drawable.sharp_filter:
			filter = new  SharpFilter();break;

	// v0.2
		// case R.drawable.invert_filter:
		// filter = new ComicFilter();break;
		// case R.drawable.invert_filter:
		// filter = new SceneFilter(5f, Gradient.Scene());break;// green
		// case R.drawable.invert_filter:
		// filter = new SceneFilter(5f, Gradient.Scene1());break;// purple
		// case R.drawable.invert_filter:
		// filter = new SceneFilter(5f, Gradient.Scene2());break;// blue
		// case R.drawable.invert_filter:
		// filter = new SceneFilter(5f, Gradient.Scene3());break;
		// case R.drawable.invert_filter:
		// filter = new FilmFilter(80f);break;
		// case R.drawable.invert_filter:
		// filter = new FocusFilter();break;
		// case R.drawable.invert_filter:
		// filter = new CleanGlassFilter();break;
		// case R.drawable.invert_filter:
		// filter = new PaintBorderFilter(0x00FF00);break;// green
		// case R.drawable.invert_filter:
		// filter = new PaintBorderFilter(0x00FFFF);break;// yellow
		// case R.drawable.invert_filter:
		// filter = new PaintBorderFilter(0xFF0000);break;// blue
		// case R.drawable.invert_filter:
		// filter = new LomoFilter();break;

	// v0.1
	case R.drawable.invert_filter:
			filter = new  InvertFilter();break;
	case R.drawable.blackwhite_filter:
			filter = new  BlackWhiteFilter();break;
	case R.drawable.edge_filter: filter = new  EdgeFilter();break;
	case R.drawable.pixelate_filter:
			filter = new  PixelateFilter();break;
			case R.drawable.neon_filter: filter = new  NeonFilter();break;
	case R.drawable.bigbrother_filter:
			filter = new  BigBrotherFilter();break;
	case R.drawable.monitor_filter:
			filter = new  MonitorFilter();break;
	case R.drawable.relief_filter:
			filter = new  ReliefFilter();break;
	case R.drawable.brightcontrast_filter:
			filter = new  BrightContrastFilter();break;
	case R.drawable.saturationmodity_filter:
			filter = new  SaturationModifyFilter();break;
	case R.drawable.threshold_filter:
			filter = new  ThresholdFilter();break;
	case R.drawable.noisefilter:
			filter = new  NoiseFilter();break;
	case R.drawable.banner_filter1:
			filter = new  BannerFilter(10, true);break;
	case R.drawable.banner_filter2:
			filter = new  BannerFilter(10, false);break;
	case R.drawable.rectmatrix_filter:
			filter = new  RectMatrixFilter();break;
	case R.drawable.blockprint_filter:
			filter = new  BlockPrintFilter();break;
	case R.drawable.brick_filter:
			filter = new  BrickFilter();break;
	case R.drawable.gaussianblur_filter:
			filter = new  GaussianBlurFilter();break;
	case R.drawable.light_filter:
			filter = new  LightFilter();break;
	case R.drawable.mosaic_filter:
			filter = new  MistFilter();break;
//	case R.drawable.mosaic_filter:
//			filter = new  MosaicFilter();break;
	case R.drawable.oilpaint_filter:
			filter = new  OilPaintFilter();break;
	case R.drawable.radialdistortion_filter:
			filter = new  RadialDistortionFilter();break;
	case R.drawable.reflection1_filter:
			filter = new  ReflectionFilter(true);break;
	case R.drawable.reflection2_filter:
			filter = new  ReflectionFilter(false);break;
	case R.drawable.saturationmodify_filter:
			filter = new  SaturationModifyFilter();break;
	case R.drawable.smashcolor_filter:
			filter = new  SmashColorFilter();break;
	case R.drawable.tint_filter: filter = new  TintFilter();break;
	case R.drawable.vignette_filter:
			filter = new  VignetteFilter();break;
	case R.drawable.autoadjust_filter:
			filter = new  AutoAdjustFilter();break;
	case R.drawable.colorquantize_filter:
			filter = new  ColorQuantizeFilter();break;
	case R.drawable.waterwave_filter:
			filter = new  WaterWaveFilter();break;
	case R.drawable.vintage_filter:
			filter = new  VintageFilter();break;
	case R.drawable.oldphoto_filter:
			filter = new  OldPhotoFilter();break;
	case R.drawable.sepia_filter:
			filter = new  SepiaFilter();break;
	case R.drawable.rainbow_filter:
			filter = new  RainBowFilter();break;
	case R.drawable.feather_filter:
			filter = new  FeatherFilter();break;
	case R.drawable.xradiation_filter:
			filter = new  XRadiationFilter();break;
	case R.drawable.nightvision_filter:
			filter = new  NightVisionFilter();break;
}
	return filter;
	
}
}
