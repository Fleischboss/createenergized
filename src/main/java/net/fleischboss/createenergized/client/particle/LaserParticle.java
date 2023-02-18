package net.fleischboss.createenergized.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.fleischboss.createenergized.particle.LaserParticleData;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

/*
 [======================================================]
 | Credits to Mekanism mod for this laser particle code |
 [======================================================]
*/

public class LaserParticle extends TextureSheetParticle {

    private static final float RADIAN_45 = (float) Math.toRadians(45);
    private static final float RADIAN_90 = (float) Math.toRadians(90);

    private final Direction direction;
    private final float halfLength;

    protected LaserParticle(ClientLevel level, Vec3 start, Vec3 end, Direction direction, float scale) {
        super(level, (start.x + end.x) / 2d, (start.y + end.y) / 2d, (start.z + end.z) / 2d);
        rCol = 0.2f; gCol = 0.6f; bCol = 0.8f; alpha = 0.11f;
        quadSize = scale;
        this.halfLength = (float) start.distanceTo(end) / 2;
        this.direction = direction;
        updateBoundingBox();
    }

    @Override
    public void render(@NotNull VertexConsumer vertexBuilder, Camera renderInfo, float partialTicks) {
        Vec3 view = renderInfo.getPosition();
        float newX = (float) (Mth.lerp(partialTicks, xo, x) - view.x());
        float newY = (float) (Mth.lerp(partialTicks, yo, y) - view.y());
        float newZ = (float) (Mth.lerp(partialTicks, zo, z) - view.z());
        float uMin = getU0();
        float uMax = getU1();
        float vMin = getV0();
        float vMax = getV1();
        Quaternion quaternion = direction.getRotation();
        quaternion.mul(Vector3f.YP.rotation(RADIAN_45));
        drawComponent(vertexBuilder, getResultVector(quaternion, newX, newY, newZ), uMin, uMax, vMin, vMax);
        Quaternion quaternion2 = new Quaternion(quaternion);
        quaternion2.mul(Vector3f.YP.rotation(RADIAN_90));
        drawComponent(vertexBuilder, getResultVector(quaternion2, newX, newY, newZ), uMin, uMax, vMin, vMax);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    private Vector3f[] getResultVector(Quaternion quaternion, float newX, float newY, float newZ) {
        Vector3f[] resultVector = {
                new Vector3f(-quadSize, -halfLength, 0),
                new Vector3f(-quadSize, halfLength, 0),
                new Vector3f(quadSize, halfLength, 0),
                new Vector3f(quadSize, -halfLength, 0)
        };
        for (Vector3f vec : resultVector) {
            vec.transform(quaternion);
            vec.add(newX, newY, newZ);
        }
        return resultVector;
    }

    private void drawComponent(VertexConsumer vertexBuilder, Vector3f[] resultVector, float uMin, float uMax, float vMin, float vMax) {
        addVertex(vertexBuilder, resultVector[0], uMax, vMax);
        addVertex(vertexBuilder, resultVector[1], uMax, vMin);
        addVertex(vertexBuilder, resultVector[2], uMin, vMin);
        addVertex(vertexBuilder, resultVector[3], uMin, vMax);
        //Draw back faces
        addVertex(vertexBuilder, resultVector[1], uMax, vMin);
        addVertex(vertexBuilder, resultVector[0], uMax, vMax);
        addVertex(vertexBuilder, resultVector[3], uMin, vMax);
        addVertex(vertexBuilder, resultVector[2], uMin, vMin);
    }

    private void addVertex(VertexConsumer vertexBuilder, Vector3f pos, float u, float v) {
        vertexBuilder.vertex(pos.x(), pos.y(), pos.z()).uv(u, v).color(rCol, gCol, bCol, alpha).uv2(240, 240).endVertex();
    }

    @Override
    protected void setSize(float particleWidth, float particleHeight) {
        if (particleWidth != this.bbWidth || particleHeight != this.bbHeight) {
            this.bbWidth = particleWidth;
            this.bbHeight = particleHeight;
        }
    }

    @Override
    public void setPos(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        if (direction != null) {
            //Direction can be null when the super constructor is calling this method
            updateBoundingBox();
        }
    }

    private void updateBoundingBox() {
        float halfDiameter = quadSize / 2;
        setBoundingBox(switch (direction) {
            case DOWN, UP -> new AABB(x - halfDiameter, y - halfLength, z - halfDiameter, x + halfDiameter, y + halfLength, z + halfDiameter);
            case NORTH, SOUTH -> new AABB(x - halfDiameter, y - halfDiameter, z - halfLength, x + halfDiameter, y + halfDiameter, z + halfLength);
            case WEST, EAST -> new AABB(x - halfLength, y - halfDiameter, z - halfDiameter, x + halfLength, y + halfDiameter, z + halfDiameter);
        });
    }

    public static class Factory implements ParticleProvider<LaserParticleData> {

        private final SpriteSet spriteSet;

        public Factory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        private Vec3 translate(Vec3 vec3, Direction direction, double amount) {
            return vec3.add(direction.getNormal().getX() * amount, direction.getNormal().getY() * amount, direction.getNormal().getZ() * amount);
        }

        @Override
        public LaserParticle createParticle(LaserParticleData data, @NotNull ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            Vec3 start = new Vec3(x, y, z);
            Vec3 end = translate(start, data.direction(), data.distance());
            LaserParticle particleLaser = new LaserParticle(world, start, end, data.direction(), data.scale());
            particleLaser.pickSprite(this.spriteSet);
            return particleLaser;
        }
    }
}
