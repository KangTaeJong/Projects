#include <iostream>
#include <math.h>
#include <set>
using namespace std;
typedef long long ll;

ll getDecimal(string a,ll number){
    ll res = 0;
    int cnt=0;
    for(int i=a.size()-1;i>=0;i--){
        res += pow(number,cnt++) * (a[i]-'0');
    }
    return res;
}

int main() {
    ios::sync_with_stdio(false);cin.tie();cout.tie();
    int tc; cin>>tc;
    int testnum = 1;
    while(tc--){
        string bin, tri;
        cin>>bin>>tri;
        //cout << getDecimal(bin,2) << " " << getDecimal(tri,3) << endl;
        if(tri.size() > 26)
        {
            tri[0] = '0';
            cout <<"#"<<testnum++<<" "<<getDecimal(tri,3) << '\n';
            continue;
        }
        set<ll> se;
        ll ans = 0;
        for(int i=0; i<bin.size(); i++)
        {
            char temp = bin[i];
            if(bin[i]=='0'){
                bin[i] = '1';
            }
            else
                bin[i] = '0';
            se.insert(getDecimal(bin,2));
            bin[i] = temp;
        }
        set<ll>::iterator iter;
        for(int i=0; i<tri.size(); i++)
        {
            char temp = tri[i];
            if(tri[i]=='0'){
                tri[i] = '1';
               if(se.find(getDecimal(tri,3))!=se.end()){
                    ans = getDecimal(tri,3);
                    break;
                }
                tri[i] = '2';
                if(se.find(getDecimal(tri,3))!=se.end()){
                    ans = getDecimal(tri,3);
                    break;
                }
            }
            else if(tri[i] == '1'){
                tri[i] = '0';
                if(se.find(getDecimal(tri,3))!=se.end()){
                    ans = getDecimal(tri,3);
                    break;
                }
                tri[i] = '2';
                if(se.find(getDecimal(tri,3))!=se.end()){
                    ans = getDecimal(tri,3);
                    break;
                }
            }
            else{
                tri[i] = '1';
                if(se.find(getDecimal(tri,3))!=se.end()){
                    ans = getDecimal(tri,3);
                    break;
                }
                tri[i] = '0';
                if(se.find(getDecimal(tri,3))!=se.end()){
                    ans = getDecimal(tri,3);
                    break;
                }
            }
            tri[i] = temp;
        }


        cout <<"#"<<testnum++<<" "<<ans << '\n';
    }
}