package devkng.com.blogbite.authentication.switch_account_list;

import io.realm.Login_accounts_list_modelRealmProxyInterface;
import io.realm.RealmObject;
import io.realm.internal.RealmObjectProxy;

/* loaded from: classes4.dex */
public class Login_accounts_list_model extends RealmObject implements Login_accounts_list_modelRealmProxyInterface {
    public static final int LAYOUT_CURRENT_ACCOUNT = 1;
    public static final int LAYOUT_OTHER_ACCOUNTS = 2;
    String e;
    String p;

    @Override // io.realm.Login_accounts_list_modelRealmProxyInterface
    public String realmGet$e() {
        return this.e;
    }

    @Override // io.realm.Login_accounts_list_modelRealmProxyInterface
    public String realmGet$p() {
        return this.p;
    }

    @Override // io.realm.Login_accounts_list_modelRealmProxyInterface
    public void realmSet$e(String str) {
        this.e = str;
    }

    @Override // io.realm.Login_accounts_list_modelRealmProxyInterface
    public void realmSet$p(String str) {
        this.p = str;
    }

    public Login_accounts_list_model() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy) this).realm$injectObjectContext();
        }
    }

    public String getE() {
        return realmGet$e();
    }

    public void setE(String e) {
        realmSet$e(e);
    }

    public String getP() {
        return realmGet$p();
    }

    public void setP(String p) {
        realmSet$p(p);
    }
}