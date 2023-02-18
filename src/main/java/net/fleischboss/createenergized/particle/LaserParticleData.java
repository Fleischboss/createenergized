package net.fleischboss.createenergized.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public record LaserParticleData(Direction direction, double distance, float scale) implements ParticleOptions {
    public static final ParticleOptions.Deserializer<LaserParticleData> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        @NotNull
        @Override
        public LaserParticleData fromCommand(@NotNull ParticleType<LaserParticleData> type, @NotNull StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            Direction direction = Direction.from3DDataValue(reader.readInt());
            reader.expect(' ');
            double distance = reader.readDouble();
            reader.expect(' ');
            float energyScale = reader.readFloat();
            return new LaserParticleData(direction, distance, energyScale);
        }

        @NotNull
        @Override
        public LaserParticleData fromNetwork(@NotNull ParticleType<LaserParticleData> type, FriendlyByteBuf buf) {
            return new LaserParticleData(buf.readEnum(Direction.class), buf.readDouble(), buf.readFloat());
        }
    };

    public static final Codec<LaserParticleData> CODEC = RecordCodecBuilder.create(val -> val.group(
            Direction.CODEC.fieldOf("direction").forGetter(data -> data.direction),
            Codec.DOUBLE.fieldOf("distance").forGetter(data -> data.distance),
            Codec.FLOAT.fieldOf("energyScale").forGetter(data -> data.scale)
    ).apply(val, LaserParticleData::new));

    @Override
    public ParticleType<?> getType() {
        return ParticleRegistry.LASER.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeEnum(direction);
        buffer.writeDouble(distance);
        buffer.writeFloat(scale);
    }

    @Override
    public String writeToString() {
        return String.format(Locale.ROOT, "%s %d %.2f %.2f", ParticleRegistry.LASER.get(), direction.ordinal(), distance, scale);
    }
}
