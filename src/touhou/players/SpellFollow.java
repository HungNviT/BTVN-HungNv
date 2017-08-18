package touhou.players;

import bases.GameObject;
import bases.Vector2D;
import touhou.enemies.Enemy;

public class SpellFollow extends PlayerSpell {
    Enemy followEnemy;
    float speed = 10;
    //tim ra enemy >> mo vao GameObject find ra 1 con enemy >> luu lai con enemy
    //xac dinh duong bay den enemy tim ra
    //bay

    @Override
    public void run(Vector2D parentPosition) {
        super.run(parentPosition);
        //tim ra 1 con enemy de duoi theo
        if(followEnemy == null) {
            //bay len 10px nhu PlayerSpell binh thuong
            followEnemy = GameObject.find(Enemy.class);
        } else {
            if(followEnemy.isActive()) {
                //da tim thay 1 con
                //ko bay 10px len nhu binh tuhong nua -10 + 10 = 0
                position.addUp(0, 10);
                Vector2D directionToEnemy = followEnemy.getScreenPosition().subtract(this.screenPosition);
                //directionToEnemy vecto dung huong nhung ma sai luc
                Vector2D velocityToEnemy = directionToEnemy.normalizeThis().multiplyThis(speed);
                //velocityToEnemy la 1 vecto vua dung huong, vua dung luc
                this.position.addUp(velocityToEnemy);
            } else {
                followEnemy = GameObject.find(Enemy.class);
            }
        }
    }
}
