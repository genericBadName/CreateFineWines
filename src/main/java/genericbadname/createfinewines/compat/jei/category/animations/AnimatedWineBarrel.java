package genericbadname.createfinewines.compat.jei.category.animations;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import genericbadname.createfinewines.register.content.ModBlocks;

public class AnimatedWineBarrel extends AnimatedKinetics {

    @Override
    public void draw(PoseStack matrixStack, int xOffset, int yOffset) {
        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, 200);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(-15.5f));
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(22.5f));
        int scale = 23;


        blockElement(ModBlocks.WINE_BARREL.getDefaultState())
                .atLocal(0, 0-.25, 0)
                .scale(scale)
                .render(matrixStack);


        matrixStack.popPose();
    }

}
