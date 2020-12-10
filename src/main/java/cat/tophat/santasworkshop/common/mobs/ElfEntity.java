package cat.tophat.santasworkshop.common.mobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ElfEntity extends MonsterEntity implements IAnimatable {

    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.createKey(ElfEntity.class, DataSerializers.BOOLEAN);
    private AnimationFactory FACTORY = new AnimationFactory(this);

    public ElfEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        experienceValue = 2;
    }

    public static AttributeModifierMap.MutableAttribute createElfAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 8.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.0F)
                .createMutableAttribute(Attributes.ARMOR, 1.0F);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(2, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
        goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.5D, false));
        goalSelector.addGoal(4, new LookRandomlyGoal(this));
        goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        goalSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isAttacking() {
        return dataManager.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        dataManager.set(ATTACKING, attacking);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(ATTACKING, false);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.elf.walking", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.FACTORY;
    }
}
