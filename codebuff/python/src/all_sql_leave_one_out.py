#
# AUTO-GENERATED FILE. DO NOT EDIT
# CodeBuff 1.4.15 'Wed May 18 13:26:32 PDT 2016'
#
import matplotlib.pyplot as plt

sqlite_noisy_dist = [0.09836066, 0.022033898, 0.17368421, 0.2141707, 0.18779589, 0.14686924, 0.18441814, 0.054758802, 0.124442354, 0.3275449, 0.30130294, 0.18965517, 0.25156575, 0.17710997, 0.15145873, 0.07841709, 0.13931298, 0.05945604, 0.12756215, 0.17483108, 0.25382832, 0.1692503, 0.050943397, 0.29538462, 0.29448563, 0.096440874, 0.17417678, 0.10140562, 0.42797562, 0.18231541, 0.2881356, 0.12930804, 0.13706197, 0.4048535, 0.25200784, 0.2426739]
sqlite_noisy_err = [0.13043478, 0.05154639, 0.16666667, 0.19277108, 0.21613833, 0.16488223, 0.20158103, 0.23076923, 0.3004695, 0.1767442, 0.2877698, 0.35341364, 0.14965986, 0.17948718, 0.13783784, 0.16071428, 0.2375, 0.0954142, 0.14021571, 0.3480663, 0.18771331, 0.11507192, 0.12962963, 0.25609756, 0.082474224, 0.17699115, 0.18137255, 0.12195122, 0.2031746, 0.20421052, 0.15662651, 0.24232633, 0.16241299, 0.15104167, 0.23387873, 0.20476191]
sqlite_dist = [0.051136363, 0.12647554, 0.13404508, 0.1653944, 0.100965105, 0.13206628, 0.12976141, 0.053326294, 0.1389049, 0.29259777, 0.08171206, 0.073387094, 0.12825397, 0.16488846, 0.21766561, 0.17828201, 0.06295051, 0.12, 0.017766498, 0.08073557, 0.15343915, 0.1861004, 0.23136717, 0.042955328, 0.09806695, 0.04945904, 0.14314449, 0.045454547, 0.01772264, 0.3630448, 0.24878557, 0.06834991, 0.13519663, 0.23031302, 0.29133984, 0.20620084]
sqlite_err = [0.02173913, 0.13095239, 0.09322034, 0.13978495, 0.112526536, 0.12707183, 0.20600858, 0.09163347, 0.1446281, 0.08108108, 0.06081081, 0.08365019, 0.088652484, 0.103896104, 0.13819095, 0.12345679, 0.086448595, 0.105882354, 0.033492822, 0.096352376, 0.11954766, 0.15254237, 0.14150943, 0.07826087, 0.109725684, 0.06410257, 0.120440066, 0.045454547, 0.02793296, 0.13432837, 0.14677104, 0.070336394, 0.11606218, 0.11358025, 0.16992882, 0.13303168]
tsql_noisy_dist = [0.0726257, 0.022033898, 0.23188406, 0.18421052, 0.1662283, 0.14548802, 0.11126827, 0.16565247, 0.09313155, 0.39137214, 0.30509746, 0.26384366, 0.302714, 0.056074765, 0.11660079, 0.09351145, 0.066521004, 0.1731419, 0.09747056, 0.2146606, 0.29583976, 0.33452633, 0.035849057, 0.13214473, 0.09190809, 0.058553386, 0.15247019, 0.32157692, 0.23518687, 0.13145539, 0.12927757, 0.14373498, 0.3435682, 0.16183333, 0.2810994, 0.25643808]
tsql_noisy_err = [0.13333334, 0.05102041, 0.20731707, 0.19318181, 0.24431819, 0.18859649, 0.2746479, 0.1983471, 0.22891566, 0.23041475, 0.39112905, 0.23404256, 0.21917808, 0.16788322, 0.16666667, 0.2125, 0.12280702, 0.35359117, 0.12654321, 0.19186492, 0.26993865, 0.07020548, 0.083333336, 0.25, 0.11873351, 0.112068966, 0.1407767, 0.13370998, 0.2079832, 0.087628864, 0.18769231, 0.24183007, 0.11627907, 0.13879408, 0.20209724, 0.21345165]
tsql_dist = [0.051136363, 0.0994941, 0.107947804, 0.14492753, 0.09844054, 0.086391434, 0.13415655, 0.39455307, 0.040126715, 0.1723343, 0.089516126, 0.050583657, 0.09372893, 0.12512124, 0.29865205, 0.13614263, 0.047573283, 0.086045824, 0.01427372, 0.08557879, 0.106541604, 0.18301158, 0.04639175, 0.21465969, 0.054929577, 0.012269938, 0.11664296, 0.12520953, 0.024550635, 0.22519083, 0.117156476, 0.18596148, 0.118186206, 0.19769357, 0.19796954, 0.12833889]
tsql_err = [0.02173913, 0.11764706, 0.075630255, 0.14893617, 0.08310992, 0.11612903, 0.13983051, 0.10358566, 0.09589041, 0.1125, 0.11026616, 0.046979867, 0.075, 0.07692308, 0.13, 0.083333336, 0.07339449, 0.11126374, 0.023809524, 0.10192024, 0.11556982, 0.09677419, 0.060869563, 0.1577287, 0.07263923, 0.007575758, 0.047826085, 0.10996564, 0.083798885, 0.14563107, 0.069536425, 0.07230769, 0.08527919, 0.1127451, 0.11852502, 0.07671601]

language_data = [sqlite_dist, sqlite_err, sqlite_noisy_dist, sqlite_noisy_err, tsql_dist, tsql_err, tsql_noisy_dist, tsql_noisy_err]
labels = ["sqlite\nn=36", "sqlite_err\nn=36", "sqlite_noisy\nn=36", "sqlite_noisy_err\nn=36", "tsql\nn=36", "tsql_err\nn=36", "tsql_noisy\nn=36", "tsql_noisy_err\nn=36"]
fig = plt.figure()
ax = plt.subplot(111)
ax.boxplot(language_data,
           whis=[10, 90], # 10 and 90 % whiskers
           widths=.35,
           labels=labels)
ax.set_xticklabels(labels, rotation=60, fontsize=8)
plt.xticks(range(1,len(labels)+1), labels, rotation=60)
ax.yaxis.grid(True, linestyle='-', which='major', color='lightgrey', alpha=0.5)
ax.set_xlabel("Grammar and corpus size")
ax.set_ylabel("Edit distance / size of file")
ax.set_title("Leave-one-out Validation Using Edit Distance / Error Rate\nBetween Formatted and Original File")
plt.tight_layout()
fig.savefig('images/all_sql_leave_one_out.pdf', format='pdf')
plt.show()
