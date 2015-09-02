package service;

import dao.SellerDao;
import domain.Seller;

/**
 * Created by Lixiao on 8/28/2015.
 */
public class SellerService {
    private SellerDao sellerDao;

    public Seller getSellerByTel(String tel) {
            return sellerDao.getSellerByTel(tel);
    }

    /**
     * updte seller's tel
     */
    public boolean updatePassword(Seller seller){
        return sellerDao.updatePassword(seller);
    }
    /**
     * update seller
     */

    public void update(Seller seller){
        sellerDao.update(seller);
    }

        /**
         * 保存seller
         */
    public void save(Seller seller){
        sellerDao.save(seller);
    }

    /**
     * 得到seller
     * @param seller
     * @return
     */
    public Seller getSellerByUnamePwd(Seller seller){
        return sellerDao.getSellerByUnamePwd(seller);
    }

    /**
     * 验证数据库有没有相同的手机号
     * @param sellerDao
     */
    public String getTel(String tel){
        return sellerDao.getTel(tel);
    }

    public void setSellerDao(SellerDao sellerDao) {
        this.sellerDao = sellerDao;
    }
}
