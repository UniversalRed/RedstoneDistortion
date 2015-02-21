package redstonedistortion.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSolarPanel2 extends ModelBase
{
  //fields
    ModelRenderer body;
    ModelRenderer supportright;
    ModelRenderer supportleft;
    ModelRenderer panel;
    ModelRenderer bodybottom;
    ModelRenderer bodytop;
    ModelRenderer leftguard;
    ModelRenderer rightguard;
  
  public ModelSolarPanel2()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      body = new ModelRenderer(this, 0, 0);
      body.addBox(0F, 0F, 0F, 14, 6, 10);
      body.setRotationPoint(-7F, 18F, -5F);
      body.setTextureSize(128, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      supportright = new ModelRenderer(this, 52, 1);
      supportright.addBox(0F, 0F, 0F, 1, 8, 8);
      supportright.setRotationPoint(7F, 16F, -4F);
      supportright.setTextureSize(128, 64);
      supportright.mirror = true;
      setRotation(supportright, 0F, 0F, 0F);
      supportleft = new ModelRenderer(this, 73, 1);
      supportleft.addBox(0F, 0F, 0F, 1, 8, 8);
      supportleft.setRotationPoint(-8F, 16F, -4F);
      supportleft.setTextureSize(128, 64);
      supportleft.mirror = true;
      setRotation(supportleft, 0F, 0F, 0F);
      panel = new ModelRenderer(this, 0, 19);
      panel.addBox(0F, 0F, 0F, 14, 1, 12);
      panel.setRotationPoint(-7F, 16F, -6F);
      panel.setTextureSize(128, 64);
      panel.mirror = true;
      setRotation(panel, 0F, 0F, 0F);
      bodybottom = new ModelRenderer(this, 94, 11);
      bodybottom.addBox(0F, 0F, 0F, 14, 5, 2);
      bodybottom.setRotationPoint(-7F, 19F, 5F);
      bodybottom.setTextureSize(128, 64);
      bodybottom.mirror = true;
      setRotation(bodybottom, 0F, 0F, 0F);
      bodytop = new ModelRenderer(this, 94, 1);
      bodytop.addBox(0F, 0F, 0F, 14, 5, 2);
      bodytop.setRotationPoint(-7F, 19F, -7F);
      bodytop.setTextureSize(128, 64);
      bodytop.mirror = true;
      setRotation(bodytop, 0F, 0F, 0F);
      leftguard = new ModelRenderer(this, 0, 42);
      leftguard.addBox(0F, 0F, 0F, 1, 1, 6);
      leftguard.setRotationPoint(-8F, 15F, -3F);
      leftguard.setTextureSize(128, 64);
      leftguard.mirror = true;
      setRotation(leftguard, 0F, 0F, 0F);
      rightguard = new ModelRenderer(this, 0, 52);
      rightguard.addBox(0F, 0F, 0F, 1, 1, 6);
      rightguard.setRotationPoint(7F, 15F, -3F);
      rightguard.setTextureSize(128, 64);
      rightguard.mirror = true;
      setRotation(rightguard, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    body.render(f5);
    supportright.render(f5);
    supportleft.render(f5);
    panel.render(f5);
    bodybottom.render(f5);
    bodytop.render(f5);
    leftguard.render(f5);
    rightguard.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
