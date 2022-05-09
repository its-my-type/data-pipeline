import com.sun.nio.sctp.IllegalReceiveException
import kotlin.math.*

var RECMD_RANGE = 1

val test_feature = mutableMapOf(0 to "1.4217404 -1.6245397 -0.1935217 -0.49758273 0.8195273 -1.3809662 -0.117000416 0.41492328 -1.2270844 0.8270501 -1.0350381 -2.2007632 -0.44360036 -0.8400203 1.0227816 0.90574837 1.611358 0.6445284 0.14611062 -1.4570926 -0.9783726 0.46168151 0.7942649 1.400681 2.126992 0.4698551 -0.16369681 1.6707499 1.0488359 0.5453358 0.726205 -0.2928461 -0.021015301 0.23492712 1.4031845 -1.3092977 0.9840746 1.6665832 0.7701636 0.47636747 -0.80064744 0.35046342 1.0564377 -0.4789718 -1.018072 -0.31172234 1.2973515 -0.7249888 -1.121587 -0.34547374 0.03504973 0.11000903 -0.8123222 0.6359366 -0.14078338 0.71160156 1.4683208 0.4368213 -0.7197688 -0.43738568 1.0761287 -1.246427 0.89788586 1.9583062 -0.34184238 0.94666284 -0.30947617 1.0903976 1.0595269 -0.27604702 -0.22579652 -1.1243466 0.27554333 -0.80060804 0.37662792 1.0504608 -0.44436398 0.3499525 0.21682826 -0.73432225 -0.44367993 0.78777564 0.66192555 1.8270624 0.101669826 -0.4302175 2.2272031 -1.8308938 -1.3521949 1.8342208 0.6309814 -0.66604805 -0.8435717 -0.6457729 -0.47468674 -0.50272584 -0.2757714 -1.3424172 -0.3927648 -0.19575578 -1.6728716 2.5626104 0.32741788 -0.2894223 0.9754569 -0.09288236 -2.7157454 -0.28318697 -0.06917636 -0.84516656 -0.84218735 0.16843656 0.59416175 -0.30261073 0.32688507 -0.822026 -0.74896276 0.39618614 0.5422564 -1.1977767 1.0301781 0.43286043 -0.41791195 0.8472097 0.040934272 -1.3382236 -0.8427382 -1.4173114",
    1 to "0.054607876 -2.018462 -0.36919886 -0.30685154 -0.45719734 -1.0857197 1.3110886 1.1418022 -0.6564375 0.21578607 -1.4824524 -1.4312526 -0.32673436 -0.4098431 -0.18776721 0.94034225 0.7518208 -0.71785986 -1.2646242 -0.40630898 -0.3350376 0.8342714 -1.7542852 -0.0010784417 0.5725718 -0.02713313 0.19697662 0.7956111 0.44916815 0.7691526 0.026365139 0.69408303 0.2028158 -0.37755626 -0.97149926 -1.4989821 -0.7927309 0.6180639 0.5210272 -1.5132347 0.3174664 0.7316831 -0.4480541 0.1648764 0.85337824 0.34022287 -0.04057347 1.2846018 -1.4585925 0.7934046 -0.0021342635 1.1659492 0.058984343 -0.73518926 -0.3475227 0.046640992 0.56323767 0.028110575 -0.35137755 -0.52758694 -0.6845962 0.36794662 -1.0919828 3.3540568 1.158646 0.34657687 0.13008904 -0.9737129 0.37630472 -1.7643924 -0.4293765 -0.42546728 0.037606105 -1.615147 -0.2166114 -0.6673185 -1.6009678 -0.21962549 0.6644763 0.18495728 -1.0260841 -0.06636875 -0.062336236 -0.055483665 0.8422154 -1.6189986 0.6052824 -0.5410373 -1.1034163 0.49231544 0.83467585 0.697659 -0.64966065 1.4027597 0.8338819 -1.0638626 1.6656141 -0.6021299 -1.0741143 -0.07284311 1.021798 1.0476447 -0.5222078 0.6990133 -1.3377527 0.8642752 -0.8117688 -1.6394724 -0.6575315 1.0028312 -0.8744547 0.46459246 1.2887869 0.436532 -0.47862363 -0.6130377 -0.28093997 0.88540703 -1.1612834 -1.171264 1.375616 1.7870349 -0.113234445 0.5984006 -1.0856751 -1.2672156 -0.41268334 -1.056034",
    2 to "1.4819334 -1.6855658 0.8992867 -0.48977435 0.89338374 -2.1645284 -0.6461911 -0.42738652 -0.50494933 -1.2067116 0.21334282 -1.261934 -1.1380832 0.00208994 0.3663967 -0.12607493 1.1388444 1.306514 -0.16951178 -0.094727404 -1.6567854 0.7794028 -0.7277619 -1.0418166 1.4298713 0.06672015 -0.6136311 1.7676383 -0.8468062 1.0145792 1.0592715 0.69929826 -0.015057784 0.60204434 -0.15079351 0.12410842 0.9119695 0.42277208 -0.8511536 1.6019557 -1.3015864 0.2711068 1.5137609 0.41005167 -0.826718 -1.1550545 -1.218424 1.7943908 0.83430886 1.0286041 2.5452507 -1.4558684 0.8064457 -0.30580977 0.6111658 -0.9705492 0.24559665 1.127227 -1.3363696 -0.302046 -0.4950217 -0.5098703 -0.95616984 3.1550157 0.9842087 0.35620487 -0.7219759 -0.5666711 0.26318887 0.37239766 0.55213714 -2.5260348 1.2070014 -1.2026372 -0.5875725 0.17100437 0.70733523 0.36185864 -0.3804577 0.26834184 -0.80584514 0.6699352 0.8948919 1.850751 -0.41337416 -1.010402 0.76268506 -1.3545336 -0.1472755 0.6641181 0.87189454 0.63686514 -2.2456372 -0.6919019 0.08953087 -0.5861215 -0.34012237 -1.481768 -0.9035436 -0.6983247 0.85476696 1.0698009 -0.17368802 -1.3381735 -0.5056387 0.06412041 -0.04620233 -1.8693067 0.44568506 -2.6904058 -0.7215438 -0.12679416 -0.03089504 0.9245341 2.33553 -0.6528076 1.0004001 0.72830904 0.7125456 0.19688351 0.28751236 -1.3205484 0.08491239 -0.10266898 0.1444858 -1.4037119 -0.34603682 0.38064006",
    3 to "1.4514878 -0.40312183 -1.8306237 0.83342296 0.8275682 -0.9844133 -0.112817355 1.5473322 1.131718 -0.05447669 -1.1429785 -1.3618765 -0.16367391 -1.1630278 -0.85395014 -0.3896693 -0.88713336 0.08399168 -0.3693175 -1.0280738 0.39763913 0.47894174 -1.8039858 1.0547702 -0.5261506 0.94712585 0.18445367 -0.17859057 -0.6499038 -1.2150023 -0.31810516 3.2636034 -0.7956009 -0.08683757 -0.5844076 -2.0588248 -0.4376338 -0.592314 0.19860706 -1.7311211 0.58921516 0.10619769 -0.444963 -0.9223488 -1.6462755 0.7966071 1.7035897 0.38787308 -0.4499668 0.82724226 0.18521807 0.38127825 -0.040641658 0.31626707 2.5149877 -0.25235528 -0.47131142 -1.8602846 0.7706469 -0.26170415 -1.6951325 -0.52517027 0.50878227 1.8429824 1.8740637 0.27736315 -0.3419011 0.28508472 0.55525726 -0.4083928 -0.46449015 -1.2110493 -0.7298073 0.8432056 -1.4635565 -0.15950762 -2.3379123 0.50565946 -0.031771604 -1.8953707 1.0447242 -2.3278306 0.057242654 0.47921124 1.6512239 -1.821322 -0.9348225 -0.71064705 -0.5693424 -0.10846411 1.4678988 -0.5507444 -1.5369424 -0.8603128 0.11515938 0.5540525 1.2600303 0.07556859 0.281163 -1.3369874 -2.1689634 0.525434 -0.56063974 -0.83912534 -0.8334341 0.4552368 -0.07000042 1.0540367 -1.0120714 0.49051073 -0.46354318 -0.071969345 -0.37629634 -0.37631652 -0.67715716 0.37760052 -0.7914454 -0.9426749 -0.19924614 -0.5739293 -0.19821636 -0.39382866 -0.009916227 1.5098373 -0.06909342 -1.6341654 -0.6931725 -1.1929876",
    4 to "-1.0502961 -0.7239573 -0.7711461 1.7351886 1.3277478 -1.9607801 0.7020369 -0.71162575 -0.119967274 -1.2703636 -0.026577812 -1.6624445 0.7218601 0.43354902 1.0464013 -0.34866625 0.45379776 0.25432777 -0.58401835 1.3199761 -1.3781651 0.8341878 -0.8194432 0.14534658 0.01018835 -0.34215173 0.23164785 0.9244087 -0.7245957 0.42065546 1.8166323 -0.1543363 1.2223277 -0.0061392337 -0.5058163 -1.1686534 0.7424145 0.12402935 2.1645298 0.40562195 -0.34304163 0.74075186 0.422648 -0.9164413 -0.61633873 0.44113848 -1.3863261 0.5104952 0.1322947 0.6895206 0.90555 1.2614777 -0.26875696 -0.23169667 0.3231094 0.1353275 1.7232563 1.5614815 -0.6326685 -1.2508131 0.68944204 0.22360279 -0.22471404 2.9031413 0.77789897 0.10875171 0.8229315 -0.7813863 -0.48614398 -0.31922838 0.015654607 -1.8706294 0.79944396 -0.5982085 -2.135965 0.1051095 0.06763776 -0.31193793 -0.8188627 0.052314803 0.21864396 1.3192929 1.5612279 2.3276622 -0.8057441 -0.22930942 0.9379299 0.3811195 -3.4342742 -0.7521674 1.3554239 0.54560417 -1.1621635 0.6643948 -0.90171146 -1.525091 0.27467984 -0.7215983 -1.755461 -0.67016286 -2.231777 0.89975363 -0.32221454 0.5139442 0.01674807 -0.4457438 0.05860037 -1.2735605 0.044800118 -0.9529055 -1.3318077 -0.6855009 1.3346157 1.0133599 0.27738997 -0.37422833 0.546515 0.57742494 0.7843415 0.75873584 -0.7802219 0.92779624 0.962574 -0.9673762 0.37776464 -1.3308023 0.097961195 0.08262627",
    5 to "0.47624156 0.6778779 1.4177969 -0.712825 -1.6892003 0.63253963 -0.009997562 0.2890578 1.2464432 -1.6730412 -1.8894278 -1.8136982 -0.48487997 0.4430299 0.49383003 -0.33414686 -0.35962462 0.9447051 -0.026257694 -0.4822919 -0.80459654 0.5923916 -1.8518727 -1.2161587 -0.642001 -1.0736091 -0.89944315 0.6877821 -2.220483 0.920535 -0.9092722 -0.50377274 -0.42972612 0.75090307 0.015463848 -1.1555449 1.2298241 -1.2172924 1.4504309 -0.12208998 1.3922786 -1.3163824 -1.4934531 -0.1604113 0.3234752 -0.19264399 0.8930779 -0.004386032 -0.47867173 -0.9988518 -0.006376952 -0.055188004 -1.2285664 -1.1784775 0.362726 -0.3512026 2.4226892 0.44463864 -0.6181617 -0.89709383 1.0154227 0.5211362 -0.36544794 2.9516711 1.1882747 1.0153071 0.775254 -1.5008785 -0.24416438 -1.8553838 0.52716964 -1.5077573 2.197184 -1.305087 0.15202229 1.225755 -1.5823298 0.6581556 1.2005048 0.27912945 0.07423198 -0.7424412 1.1529193 0.91766745 1.1166937 -2.1195157 0.906816 0.45505458 -1.2658863 1.2426099 0.47107458 0.13227345 -1.4584061 -0.4643198 -1.1857222 0.20427434 0.30000994 -0.16750216 -0.94827986 0.4020897 -0.5726215 1.9526913 -1.720109 0.066165335 0.08587467 0.42361432 -1.3102562 -0.031710558 -0.24217547 0.13408226 -1.4504049 -0.35194767 0.050001595 -0.5190221 -1.3949751 0.046402812 0.48238483 0.5378403 0.25628853 -0.22585739 -0.28367472 0.8174696 -0.03833107 0.41561732 0.16420145 -1.3179811 -0.9765634 -0.96774054",
    6 to "1.7528894 -0.1819356 -1.6303252 -0.21175009 -0.5317087 -2.0159981 -1.4807899 -0.18059403 -0.201742 0.22623199 -0.1929448 -1.5001174 -1.3062084 0.2310291 1.570706 -0.1835403 0.25502676 0.868462 -0.4813283 -1.0293804 -1.1617817 0.9174887 -0.062154744 0.41649362 2.0708246 -0.2585837 -0.35686344 0.8299541 0.4534788 -0.5204304 0.483595 1.6940315 1.0641963 -0.1164532 0.3125503 -0.97999454 1.4134898 0.37394434 -0.046929553 -0.96258855 -0.3799804 0.30809277 -0.20458779 -0.46985254 -1.749205 0.10887352 -0.5794872 -0.5073259 -0.82649124 0.5739564 1.9647157 -1.41909 -0.61689883 0.33502835 1.7429018 0.10474607 0.73788923 0.9087726 -1.2739067 -2.495326 0.89500153 -0.62813276 -0.019568566 2.3062682 0.9899642 0.41066533 -0.5334733 -0.86926186 0.018982261 -1.0548627 -0.07523948 -1.7562743 0.88467944 -0.7909087 -0.9818355 -0.33649305 -0.24080448 0.071334235 0.5552065 -0.91637856 0.41102746 0.73314357 0.51568764 2.60896 -0.6627056 -1.578784 0.31284347 -0.9720025 -0.43420088 -0.502195 1.0902826 -0.9145681 -1.4868907 -0.18704349 0.07902139 -0.6733263 1.1756015 -0.39171 -0.97230375 -1.8808041 -2.4762704 1.5336206 -0.4754491 -0.22533326 1.191267 -0.5378192 0.053812843 -0.18416858 1.1434549 -0.071797326 -1.6830102 -0.32872736 0.71178925 0.20234951 -0.23715127 -0.42356282 0.41745746 -0.66126245 0.3978172 -0.14124753 -1.19694 0.2318294 0.81369644 0.88232905 1.1480713 -0.60514885 -1.1087126 -0.45668513",
    7 to "0.7182164 -0.53308547 -0.44219497 0.8049397 1.0537246 -0.49949768 -1.3199856 0.094313845 0.24992049 -1.4391716 -0.16925198 0.22333434 -0.35720623 -0.49524316 -0.91540444 0.41377574 -0.30202657 -0.603215 -1.3108968 0.33360836 -0.30944824 0.5966411 -1.580619 -0.12939492 -0.71888924 0.22779247 0.20271467 1.1672297 1.0770606 1.4729156 1.000664 -0.12994075 -0.87756336 -0.73361856 -1.6902146 -0.005182756 -2.382739 1.0255922 0.7078354 1.3227943 0.44209597 -1.0914277 1.5247009 0.028510146 0.6926344 0.1852404 1.5400571 -0.14736255 -0.3273704 -0.23956788 -0.103186905 1.4067154 1.3679799 -0.93937963 -0.05735257 0.52702653 0.29780686 0.36352387 1.5226507 0.7755056 -1.3206699 -0.8520272 0.299071 0.6711497 0.16691732 0.036994647 0.41888747 -1.7858467 -1.4776373 -0.74266243 0.1884284 -2.065206 0.044480745 -0.14869046 -0.03575557 0.2823061 -0.23386054 1.7151153 -0.4810986 -0.6114639 0.86823064 -1.3237715 1.8952702 1.3476169 -0.12105424 0.36612758 -0.51106685 0.21779118 0.010422014 -1.2073088 1.4441944 -0.58464485 -0.25408632 -1.4878511 0.5708978 0.11296708 -0.7642482 -2.7270162 -0.35201058 -0.41806623 -1.6868519 0.20223492 0.80095047 0.71760416 -0.31715077 -0.55288315 0.96336466 1.4938958 -0.15437421 0.44471776 1.4183698 0.31211042 -0.14212547 1.4628768 0.99519426 -1.2918637 -0.09952061 2.2549858 -0.42654344 -2.3305488 1.2803279 -0.22100528 0.48729622 -0.2360464 -0.6193365 -1.338982 -0.6584972 -1.1072458",
    8 to "1.8580927 -0.15830736 -1.910154 -0.9208688 0.81612915 -1.9603299 0.05405264 -0.19468692 0.77498966 -1.2162896 -0.24225962 -1.8708258 -0.056196064 0.37992987 1.3118656 0.7867236 1.1331534 0.30958033 0.5067805 -0.646736 -1.2093959 0.30874872 -0.98514605 0.68040204 0.39385438 0.55164915 -0.46902877 0.23645948 -0.69923383 0.6348923 0.38637143 1.2368497 0.09755724 0.7256761 1.5896102 0.4471341 -0.86960787 0.9620234 0.3159488 1.7725917 -0.17386726 0.20110357 1.102604 -1.285798 -0.66811216 -1.4503524 -0.86453724 0.9317889 -0.30735677 -0.4456453 1.6536473 -0.05182087 -1.3113927 0.12967357 -0.16106981 0.022618579 0.5592357 -0.14248337 -0.4624778 -1.1685697 0.11630165 0.29678613 -0.45205155 2.3459582 0.95974684 -0.44335192 0.49752456 -0.22425185 0.5385293 0.44065487 1.563598 -2.293606 1.2841204 -1.3823718 0.04841902 -0.53628516 0.7642218 -0.37389198 -1.9954345 -2.7203171 1.0917144 0.97222245 -0.24895981 0.9421006 -0.14427468 -1.4134084 0.5760022 -2.3922696 -1.2152722 0.17638998 0.839658 -0.5497518 -0.5850133 -1.1824219 -1.6226578 0.36030087 0.5833388 -0.2320695 -1.8113861 1.2794466 -1.846046 1.5505044 0.17309567 -0.7633579 0.18335727 -0.37521565 -0.15220529 0.07396423 -0.07531637 -0.2935577 -0.6550078 0.55316114 -0.0021319836 0.18476878 0.3801163 -1.0397925 0.35366175 0.08492912 0.11568384 -0.56479454 0.3815012 0.5849047 1.2340132 -0.5508238 1.1515656 -1.2504394 -1.3397807 -0.58343476",
    9 to "1.3803935 -0.10040353 -0.3297749 -1.3286251 0.07813468 -0.3833752 -1.0866768 -0.2581771 0.16086224 -0.7702987 -0.87385666 -0.73154664 -1.5624834 -0.32596993 0.5637952 -0.3673494 1.2106951 0.5714365 0.27912444 -1.3704399 -0.3678928 -0.32306588 -0.09142238 -0.19720864 3.5641093 0.5706806 0.06516571 0.99181753 0.7120823 -0.18321975 0.06568505 1.0747571 1.6073565 -0.34248224 0.042113386 0.14859179 1.2096965 0.28794146 -1.0698943 -0.19273838 0.28083384 0.100655295 -1.1938373 1.0433178 -0.72367066 -0.28490904 -1.1556681 -0.41745675 -0.36812633 -0.6454748 -0.049079567 -1.5555224 0.39062363 0.20846815 0.8017477 1.6048852 1.6790388 -1.555061 -0.050502643 -0.29826242 0.2520167 0.35618758 -0.2432191 1.4873184 0.24547753 0.43085366 -0.30612996 -0.69943494 1.1461834 -0.56969595 -0.42590663 -0.26984417 -0.21471395 -0.92060876 -0.06870501 -0.5469078 -0.3086357 -0.69215685 1.448162 -0.4156252 0.89089066 0.09737262 -0.33146328 0.7772524 1.6713692 -1.2057785 0.8842767 -0.79141027 0.4031391 2.2565434 0.043714896 -0.22917917 -1.5843031 -1.6341066 -1.2415379 0.41826022 0.72394097 -1.4101962 -0.91913265 -0.48161602 -0.43830973 0.65325224 -0.20924652 -1.0936956 1.3365391 -0.07560028 -1.2633536 -1.3611232 1.2625958 -0.55678284 -1.9683847 -0.012891859 -0.15298706 -0.1335649 0.50606555 -0.95129895 -0.6137159 0.60189724 2.7434466 -0.09287515 1.8613752 -0.5811229 0.23371175 1.7872994 1.6632476 -0.9722123 0.8954457 0.5939929",
    10 to "1.0894672 0.46646932 0.6444875 -0.7062889 0.019097138 -0.48328748 -0.28497773 -1.0801649 0.008158829 0.19200318 0.46132237 -1.6640356 -2.8993013 0.2601872 -0.43367562 -0.32608688 0.4784362 0.77589136 0.16601798 -1.9933056 -0.33913976 -0.22848538 -0.20085737 -1.1196434 1.7160552 -0.24456546 0.27956912 0.66203254 -1.476812 0.62855107 0.21474306 1.7623668 -0.6150961 0.9341525 0.70871496 0.053646225 0.81313455 1.1084169 -0.47944903 0.716167 1.1895736 -1.0986574 -0.13808477 0.99112034 -0.17350216 -0.38116497 -0.14927165 -0.24694538 -0.12764141 -0.9359839 -0.099098325 0.21813245 0.4626477 -0.8701064 0.19120874 1.3589395 1.1939527 0.80911833 -0.82124776 -2.414517 0.90224445 0.65722823 -1.460189 2.81728 0.5591668 0.29390317 0.2469123 -0.28176734 0.18588907 -0.5503274 0.5607947 -1.9481952 1.3282504 -0.63223773 -0.36598736 0.30369276 0.30339295 0.7434289 -1.1837775 -1.016334 1.2546567 -0.062173277 0.3441253 1.3160256 0.10282747 -1.3045827 0.7220062 -0.94002473 -0.86111426 0.45912433 -0.94135994 -1.1304352 -0.30662194 -0.6648375 1.0065873 -0.54549015 0.46220884 -0.68761194 -0.11823611 0.3211189 -2.106506 1.9239291 -1.4480611 -1.9596306 0.65077466 0.34097707 0.23700228 0.67347515 0.41161612 -1.6034628 -1.8693732 1.1985672 0.47948873 0.36910167 -0.70662713 1.5786505 0.406095 -0.06837998 -0.5487472 0.70498824 -0.015023291 -0.3110897 0.8409892 0.5376031 1.6894454 -1.0780491 0.10387765 0.8068223",
    11 to "0.31719112 -0.8137748 -0.31863454 0.4439992 1.134822 -1.8422781 -0.00594436 0.8485793 1.7894311 -1.1132799 -0.057937413 -1.206857 -0.043677323 -0.6137601 -1.0910946 -0.7537416 -0.10102701 -0.872903 -1.1286377 -0.6659142 -0.71800697 0.36063913 -1.019355 0.5483222 0.15107512 -0.38403964 1.795124 0.22999173 -1.1752485 -0.3270719 -1.1384748 1.8020536 0.21432984 -0.75543034 -0.88584614 -0.8810578 -1.3681189 0.7181149 -0.050449207 -0.70925 0.2869772 -0.6250848 -0.59932756 0.18407327 -0.74938613 0.36931616 0.20278437 0.05500833 0.07122061 -0.24775991 1.4085411 0.5475271 0.73935133 -1.8677546 1.0695455 -0.33283246 0.19717464 -0.5692809 -0.08980001 -0.46625793 -0.5191317 0.43839365 -1.2904558 2.1445901 2.1654787 -0.06519285 -0.21466577 -0.26811674 0.2922743 -0.29663363 -0.8963635 -1.4768977 -1.0899694 -0.799029 -1.1521378 -0.38442454 -0.7749313 0.94080365 -0.5132016 -1.9687082 0.8068111 -0.57131827 0.9478571 1.1589869 0.3274456 -1.0372298 1.1275525 -1.5851171 -2.6246471 -1.2750567 1.761786 0.042307124 -0.782206 -0.6213152 0.49689454 -0.0061587137 1.1329592 -0.40170354 -0.87584203 -0.26927522 -2.0983276 0.59034014 0.101208806 -0.36690852 -1.0353782 0.45935506 0.10839607 -0.14100964 -1.0272024 -0.40798914 -0.6155829 -0.4814521 0.12954259 0.77746254 -0.7559202 1.303616 -0.3830968 0.878106 -0.2623632 0.3593003 -0.15532057 0.101731166 -0.24291117 0.8412391 0.8357673 -1.9831969 -1.0899465 -0.23151945",
    12 to "1.6026059 -0.52956325 -0.39219067 0.16788125 2.2952754 -2.5478795 0.31613308 -0.33858076 0.09881691 -0.22356363 -1.7592849 -1.7624983 -0.8801842 0.6758727 0.034951 -0.8330173 -0.50731385 0.093901575 -0.76562065 -0.9562714 -1.3832355 1.0448172 -0.34636474 0.23843497 -0.028474823 -0.3349156 -0.7166809 -0.34931248 -0.4802857 0.89771515 1.3684675 0.89284784 -1.1158974 0.2633951 -0.52464235 -1.8600744 0.48531914 -0.281478 0.69637287 -1.493282 -0.5839863 -0.28242514 0.3016926 0.1757726 -0.58411986 -0.101837635 -0.44641668 -0.041140422 -0.9099872 1.9561995 0.5874967 -0.85890675 -0.6258132 -0.23201144 -0.58214253 -1.248748 -0.21943891 -1.3928976 -1.1366775 -0.37218916 -0.7042746 -0.7457659 0.34678474 1.746706 -1.042814 0.19352789 -1.4498678 -1.038345 0.89774626 0.27282467 -0.09515048 -1.8270473 0.5494518 -1.2192601 -0.9176977 -0.5043185 -0.48860702 -0.46930993 0.5377223 -1.7341852 0.7306771 0.29844606 0.42906615 2.124056 -0.198695 -1.4188311 1.0636443 -1.7862114 -0.6602957 0.5844457 0.806826 -0.17289497 -2.3678646 -0.71767044 0.22968918 -0.42429194 0.8076405 -0.4020308 -0.5496333 -1.3217828 -1.4807094 1.8369793 -0.018335566 -1.0728688 1.0478245 -0.3013603 -0.2499264 -0.415071 -1.4642338 -1.2833275 -0.18347669 -0.37351346 0.087385535 0.227476 0.6563172 -0.04537286 -0.05461832 -0.029214516 -1.7802098 -0.23583113 -0.24930887 -0.47411162 0.06648214 1.3438163 -0.92907184 -2.2823467 -2.0458581 -0.6427267")


fun get_images_table(): MutableMap<Int, MutableList<Double>> {
    // images 테이블 가져와 Map<id, List<Double>>형태로 반환
    val new_map = mutableMapOf<Int, MutableList<Double>>()

    for((k, v) in test_feature){
        val str_list = v.split(" ").map {it.toDouble()}.toMutableList()
        new_map.put(k, str_list)
        //println(new_map[k])
    }
    return new_map
}

class FeatureTable() {
    val feature_map = get_images_table()

    fun get_ids(): MutableList<Int> {
        var ids = mutableListOf<Int>()
        for ((k, _) in feature_map)
            ids.add(k)
        return ids
    }

    fun get_features(ids: MutableList<Int>): MutableList<MutableList<Double>> {
        var features = mutableListOf<MutableList<Double>>()
        for (id in ids)
            features.add(feature_map[id])
        return features
    }

    fun get_ideal_feature(ids: MutableList<Int>): MutableList<Double> {
        // image_table에서 ids에 해당하는 feature들을 가져와 평균을 내어 List<Double> 형태로 반환하기
        if(ids.isEmpty())
            throw IllegalReceiveException("empty input id list")

        val features: MutableList<MutableList<Double>> = get_features(ids)
        var feature_sum = mutableListOf<Double>()
        val feature_size = features[0].size
        val ids_size = ids.size
        for (i in 1..feature_size)
            feature_sum.add(0)

        for (feature in features)
            for (i in 1..feature_size)
                feature_sum[i] += feature[i]

        for (i in 1..feature_size)
            feature_sum[i] /= ids_size

        return feature_sum
    }
}

fun euclidean_dist(v1: MutableList<Double>, v2: MutableList<Double>): Double {
    var vector_pair = v1.zip(v2)
    var squared_dists: Double = 0.0
    for(pair in vector_pair) {
        var gap = pair.first - pair.second
        squared_dists += gap.pow(2)
    }
    return sqrt(squared_dists)
}

fun random_choice(choice_list: MutableList<Int>, choice_num: Int): MutableList<Int> {
    val shuffled_seq = choice_list.asSequence().shuffled()
    val picked_seq = shuffled_seq.take(choice_num)
    return picked_seq.toList()
}


fun recmd_options(ids_chosen: MutableList<Int>, ids_not_chosen: MutableList<Int>): List<Int> {
    val feature_table = FeatureTable()
    val celebrity_ids = feature_table.get_ids()

    if(ids_chosen.isNullOrEmpty()) {
        return random_choice(celebrity_ids, 6)
    }

    var ids_can_recmd = celebrity_ids - (ids_chosen + ids_not_chosen)
    var ideal_feature = feature_table.get_ideal_feature(ids_chosen)

    var ids_high_recmd = emptyList<Int>()
    for(id in ids_can_recmd){
        var feature = feature_table.feature_map[id]
        var dist: Double = euclidean_dist(ideal_feature, feature)

        if(dist <= RECMD_RANGE)
            ids_high_recmd.add(id)
    }

    var recmd_ids = random_choice(ids_high_recmd, 3)
    ids_can_recmd -= recmd_ids
    recmd_ids.add(random_choice(ids_can_recmd), 3)

    return recmd_ids

}

fun main(){
    print(recmd_options(listOf(2), listOf(3, 6, 7, 9, 11)))
}

main()