package tehnut.quest.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import tehnut.quest.QuestBoard;
import tehnut.quest.tile.TileQuestPost;
import tehnut.quest.util.QuestUtil;

public class BlockQuestPost extends Block {

    public BlockQuestPost() {
        super(Material.WOOD);

        setCreativeTab(CreativeTabs.DECORATIONS);
        setUnlocalizedName(QuestBoard.MODID + ".questpost");
        setBlockUnbreakable();
        setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileQuestPost(QuestUtil.getRandomQuests());
    }
}
